import { Button, Table, TableContainer, Tbody, Td, Th, Thead, Tr } from "@chakra-ui/react"
import axios from "axios"
import { useEffect, useState } from "react"
import Topbar from "../components/Topbar"
import { BeerBottle, Trash } from "phosphor-react"
import AnimalInput from "../types/AnimalInput"

const baseURL = "http://localhost:8080/animais"

export function AnimalsSuckling() {
    const [animals, setAnimals] = useState([])

    function getAnimalsFromApi() {
        axios.get(baseURL + "?mamando=true").then(response => {
            setAnimals(response.data)
        })
    }

    useEffect(() => {
        getAnimalsFromApi()
    }, [])

    return (
        <>
            <Topbar />
            <TableContainer mt="0.5rem">
                <Table variant="striped" colorScheme="blackAlpha" size="sm">
                    <Thead>
                        <Tr>
                            <Th>Nro.</Th>
                            <Th>Nro. da Mãe</Th>
                            <Th>Sexo</Th>
                            <Th>Data de nascimento</Th>
                            <Th>Marca</Th>
                            <Th>Ações</Th>
                        </Tr>
                    </Thead>
                    <Tbody>
                        {animals && animals.map(({ id, numero, sexo, nascimento, marca }: AnimalInput) => {
                            return (
                                <Tr key={id.toString()}>
                                    <Td>{id}</Td>
                                    <Td>{numero}</Td>
                                    <Td>{sexo}</Td>
                                    <Td>{nascimento}</Td>
                                    <Td>{marca}</Td>
                                    <Td>
                                        <Button colorScheme="blue" size="sm" mr="0.5rem" >
                                            <BeerBottle color="white" />
                                        </Button>
                                        <Button colorScheme="red" size="sm">
                                            <Trash color="white" />
                                        </Button>
                                    </Td>
                                </Tr>
                            )
                        })}
                    </Tbody>
                </Table>
            </TableContainer>
        </>
    )
}

export default AnimalsSuckling