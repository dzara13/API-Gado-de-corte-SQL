import { Box, Button, Flex, Heading, Text } from "@chakra-ui/react"
import axios from "axios"
import { ArrowDown, ArrowLeft, ArrowUp, CalendarBlank, MagnifyingGlass } from "phosphor-react"
import { useEffect, useState } from "react"
import { Link as RouterLink } from "react-router-dom"
import Topbar from "../../components/Topbar"
import "./style.css"

interface MetricsFromApi {
    idades: Array<number>,
    mediaAnual: number,
    mediaPeriodo: number,
    numeroDeNascimentosPeriodo: number,
    contagemTotal: number
}

interface MetricCardProps {
    title: string,
    children: any
}

function MetricCard({ title, children }: MetricCardProps) {
    return (
        <Box p={10} shadow="md" borderRadius="xl" borderWidth="1px" height="16rem" width="16rem">
            <Heading as="h3" color="gray.700" mb="0.5rem" fontSize="1rem" fontWeight="normal">{title}</Heading>
            {children}
        </Box>
    )
}

export function Metrics() {
    const baseURL = "http://localhost:8080/animais"

    const [metrics, setMetrics] = useState<MetricsFromApi | {}>({})

    function getMetricsFromApi() {
        axios.get(baseURL + "/metricas")
            .then(response => {
                setMetrics(response.data)
            })
    }

    useEffect(() => {
        getMetricsFromApi()
    }, [])

    return (
        <>
            <Topbar />
            <main>
                {Object.keys(metrics).length > 0 ? (
                    <Flex height="100vh" direction="column" alignItems="center" justifyContent="center" fontSize="2xl" gap="1rem">
                        <Flex alignItems="center" gap="1rem">
                            <MagnifyingGlass size={64} />
                            <span>Ops! Não foi encontrada nenhuma métrica!</span>
                        </Flex>
                        <Button
                            colorScheme="green"
                            leftIcon={<ArrowLeft size={23} weight="bold" />}
                            as={RouterLink}
                            to="/animais"
                            size="lg">
                            Voltar
                        </Button>
                    </Flex>
                ) : (
                    <Box m="1rem">
                        <Box mb="1rem">
                            <Heading color="gray.700">Métricas dos Animais</Heading>
                            <Text size="sm" color="gray.500" fontWeight="light">Confira as métricas de todo o tempo dos animais da fazenda.</Text>
                        </Box>
                        <Flex alignItems="center" justifyContent="center" flexWrap="wrap" gap={8} height="55vh">
                            <MetricCard title="Idades">
                                <Box fontSize="1.2rem">
                                    <Flex mb="1rem" direction="column">
                                        <Flex alignItems="center" gap="0.5rem">
                                            <Text>Menor</Text>
                                            <ArrowDown size={16} weight="bold" color="#F30" />
                                        </Flex>
                                        <div>
                                            <span className="important-data">{metrics.idades[0] || "-1"}</span> {metrics.idades[0] > 1 ? "anos" : "ano"}
                                        </div>
                                    </Flex>
                                    <Box>
                                        <Flex alignItems="center" gap="0.5rem">
                                            <span>Maior</span>
                                            <ArrowUp size={16} weight="bold" color="#080" />
                                        </Flex>
                                        <span>
                                            <span className="important-data">{metrics.idades[1] || "-1"}</span> {metrics.idades[1] >= 2 ? "anos" : "ano"}
                                        </span>
                                    </Box>
                                </Box>
                            </MetricCard>
                            <MetricCard title="Média Anual de Natalidade">
                                <Flex direction="column" gap="1rem">
                                    <Box>
                                        <Box fontSize="1.2rem">
                                            <Text as="span" className="important-data" fontSize="1.4rem">{metrics.mediaAnual.toFixed(2)}</Text> {metrics.mediaAnual >= 2 ? "nascimentos" : "nascimento"}
                                        </Box>
                                        <span>por ano</span>
                                    </Box>
                                    <Flex alignItems="center" gap="0.5rem">
                                        <CalendarBlank size={16} />
                                        <span>
                                            em <span className="important-data">{metrics.idades[1]}</span> {metrics.idades[1] >= 2 ? "anos" : "ano"}
                                        </span>
                                    </Flex>
                                </Flex>
                            </MetricCard>
                            <MetricCard title="Média de Natalidade Mensal">
                                <Flex direction="column" gap="1rem">
                                    <Box>
                                        <Box fontSize="1.2rem">
                                            <Text as="span" className="important-data" fontSize="1.4rem">{metrics.mediaPeriodo.toFixed(2)}</Text> {metrics.mediaAnual >= 2 ? "nascimentos" : "nascimento"}
                                        </Box>
                                        <span>por mês</span>
                                    </Box>
                                    <Flex alignItems="center" gap="0.5rem">
                                        <CalendarBlank size={16} />
                                        <span>
                                            em <span className="important-data">{metrics.idades[1] * 12}</span> {metrics.idades[1] * 12 >= 2 ? "meses" : "mês"}
                                        </span>
                                    </Flex>
                                </Flex>
                            </MetricCard>
                            <MetricCard title="Total de Nascimentos">
                                <Flex alignItems="center" justifyContent="center" height="80%">
                                    <div>
                                        <Text as="span" fontSize="3rem">{metrics.contagemTotal}</Text> {metrics.contagemTotal >= 2 ? "nascimentos" : "nascimento"}
                                    </div>
                                </Flex>
                            </MetricCard>
                        </Flex>
                    </Box>
                )}
            </main>
        </>
    )
}

export default Metrics