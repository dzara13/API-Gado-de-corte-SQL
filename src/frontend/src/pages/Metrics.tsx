import { Box, Flex, Heading, Text } from "@chakra-ui/react"
import Topbar from "../components/Topbar"

interface MetricCardProps {
    title: string,
    description: string
}

function MetricCard({ title, description }: MetricCardProps) {
    return (
        <Box p={5} shadow="md" borderRadius="md" borderWidth="1px">
            <Heading as="h3" color="gray.700" mb="0.5rem">{title}</Heading>
            <Text fontSize="xl">{description}</Text>
        </Box>
    )
}

export function Metrics() {
    return (
        <>
            <Topbar />
            <main>
                <Box m="1rem">
                    <Box mb="1rem">
                        <Heading color="gray.700">Métricas dos Animais</Heading>
                        <Text size="sm" color="gray.500" fontWeight="light">Confira as métricas de todo o tempo dos animais da fazenda.</Text>
                    </Box>
                    <Flex maxW="100vw" flexDir="column" alignItems="center" gap={4}>
                        <Flex gap={4}>
                            <MetricCard title="Idades" description="Entre: Menos de 1 ano e 12 anos" />
                            <MetricCard title="Média Anual de Natalidade" description="1 nascimento/ano (12 anos)" />
                        </Flex>
                        <Flex gap={4}>
                            <MetricCard title="Média de Natalidade Mensal" description="0.8 nascimento/mês (144 meses)" />
                            <MetricCard title="Total de Nascimentos" description="12 animais" />
                        </Flex>
                    </Flex>
                </Box>
            </main>
        </>
    )
}

export default Metrics