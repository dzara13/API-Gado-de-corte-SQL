import { Table, TableContainer, Tbody, Td, Th, Thead, Tr } from "@chakra-ui/react"
import axios from "axios"
import { useEffect, useState } from "react"

interface Animal {
    id: number,
    numero: number,
    sexo: string,
    nascimento: string,
    marca: string,
    desmama: boolean
}

const baseURL = "http://localhost:8080/animais"

export function Home() {
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
        <TableContainer>
            <Table>
                <Thead>
                    <Tr>
                        <Th>Nro.</Th>
                        <Th>Nro. da Mãe</Th>
                        <Th>Sexo</Th>
                        <Th>Data de nascimento</Th>
                        <Th>Marca</Th>
                        <Th>Desmamado</Th>
                    </Tr>
                </Thead>
                <Tbody>
                    {animals && animals.map(({ id, numero, sexo, nascimento, marca, desmama }: Animal) => {
                        return (
                            <Tr key={id.toString()}>
                                <Td isNumeric>{id}</Td>
                                <Td>{numero}</Td>
                                <Td>{sexo}</Td>
                                <Td>{nascimento}</Td>
                                <Td>{marca}</Td>
                                <Td>{desmama ? "V" : "F"}</Td>
                            </Tr>
                        )
                    })}
                </Tbody>
            </Table>
        </TableContainer>
    )
}

export default Home