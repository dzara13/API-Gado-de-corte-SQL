import { Button, Table, TableContainer, Tbody, Td, Th, Thead, Tooltip, Tr, useToast } from "@chakra-ui/react"
import axios from "axios"
import { useEffect, useState } from "react"
import Topbar from "../components/Topbar"
import { BeerBottle, Trash } from "phosphor-react"
import AnimalInput from "../types/AnimalInput"
import toBrLocaleString from "../types/dateUtils"

const baseURL = "http://localhost:8080/animais"

export function AnimalsSuckling() {
    const [animals, setAnimals] = useState([])
    const animalWeaningToast = useToast()

    function handleAnimalWeaning(id: number) {
        axios.put(baseURL + `/${id}/desmamar`).then(response => {
            response.status == 200 && animalWeaningToast({
                title: "Animal desmamado.",
                description: `O animal de número "${id}" foi desmamado com sucesso!`,
                status: "success",
                duration: 3000,
                isClosable: true
            })

            getAnimalsFromApi()
        }).catch(err => {
            console.log(err)
            animalWeaningToast({
                title: 'Ocorreu um erro na desmama do animal.',
                status: 'error',
                duration: 3000,
                isClosable: true
            })
        })
    }

    function handleAnimalDismiss(id: number) {
        axios.delete(baseURL + `/${id}`).then(response => {
            response.status == 200 && animalWeaningToast({
                title: "Baixa aplicada.",
                description: `O a baixa no animal de número "${id}" foi aplicada com sucesso!`,
                status: "success",
                duration: 3000,
                isClosable: true
            })

            getAnimalsFromApi()
        }).catch(err => {
            console.log(err)
            animalWeaningToast({
                title: 'Ocorreu um erro no processo de animal.',
                status: 'error',
                duration: 3000,
                isClosable: true
            })
        })
    }

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
                <Table variant="striped" colorScheme="blackAlpha" size="lg">
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
                                    <Td>{toBrLocaleString(nascimento)}</Td>
                                    <Td>{marca}</Td>
                                    <Td>
                                        <Tooltip label="Desmamar">
                                            <Button colorScheme="blue" size="md" mr="0.5rem" onClick={() => handleAnimalWeaning(id)}>
                                                <BeerBottle color="white" size={20} />
                                            </Button>
                                        </Tooltip>
                                        <Tooltip label="Dar baixa">
                                            <Button colorScheme="red" size="md" onClick={() => handleAnimalDismiss(id)}>
                                                <Trash color="white" size={20} />
                                            </Button>
                                        </Tooltip>
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