import { Table, TableContainer, Tbody, Td, Th, Thead, Tr } from "@chakra-ui/react"
import axios from "axios"
import { Check, X } from "phosphor-react"
import { useEffect, useState } from "react"
import Topbar from "../components/Topbar"
import AnimalInput from "../types/AnimalInput"
import toBrLocaleString from "../utils/dateUtils"

const baseURL = "http://localhost:8080/animais"

export function AnimalsGeneral() {
    const [animals, setAnimals] = useState([])

    function getAnimalsFromApi() {
        axios.get(baseURL).then(response => {
            setAnimals(response.data)
        })
    }

    useEffect(() => {
        getAnimalsFromApi()
    }, [])

    return (
        <>
            <main>
                <Topbar />
                <TableContainer mt="0.5rem">
                    <Table variant="striped" colorScheme="blackAlpha" size="lg">
                        <Thead>
                            <Tr>
                                <Th>Nro.</Th>
                                <Th>Nro. da Mãe</Th>
                                <Th>Sexo</Th>
                                <Th>Data de nascimento</Th>
                                <Th>Marca</Th>
                                <Th>Foi desmamado</Th>
                            </Tr>
                        </Thead>
                        <Tbody>
                            {animals && animals.map(({ id, numero, sexo, nascimento, marca, desmama }: AnimalInput) => {
                                return (
                                    <Tr key={id.toString()}>
                                        <Td>{id}</Td>
                                        <Td>{numero}</Td>
                                        <Td>{sexo}</Td>
                                        <Td>{toBrLocaleString(nascimento)}</Td>
                                        <Td>{marca}</Td>
                                        <Td>{desmama ? <Check color="#4caf50" size={28} weight="bold" /> :
                                            <X color="#ef5350" size={28} weight="bold" />}</Td>
                                    </Tr>
                                )
                            })}
                        </Tbody>
                    </Table>
                </TableContainer>
            </main>
        </>
    )
}

export default AnimalsGeneral