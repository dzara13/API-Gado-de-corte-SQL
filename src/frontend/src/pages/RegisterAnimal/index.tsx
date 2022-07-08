import { Box, Button, ButtonGroup, FormControl, FormHelperText, FormLabel, Heading, HStack, Input, NumberDecrementStepper, NumberIncrementStepper, NumberInput, NumberInputField, NumberInputStepper, Radio, RadioGroup, useToast } from "@chakra-ui/react"
import { ArrowLeft, FloppyDisk } from "phosphor-react"
import Topbar from "../../components/Topbar"
import { Link as RouterLink } from "react-router-dom"

import "./style.css"
import { FormEvent, useState } from "react"
import axios from "axios"

const baseURL = "http://localhost:8080/animais"

export function RegisterAnimal() {
    const [motherNumber, setMotherNumber] = useState(1)
    const [sex, setSex] = useState("M")
    const [birthDate, setBirthDate] = useState("")
    const [brand, setBrand] = useState("")
    const animalRegistredToast = useToast()

    function handleAnimalRegistration(event: FormEvent) {
        event.preventDefault()

        axios.post(baseURL, {
            numero: motherNumber,
            sexo: sex,
            nascimento: birthDate,
            marca: brand
        }).then(() => {
            animalRegistredToast({
                title: "Animal registrado.",
                description: "O animal foi cadastrado com sucesso!",
                status: "success",
                duration: 3000,
                isClosable: true
            })
        }).catch((err) => {
            console.log(err)
            animalRegistredToast({
                title: "Ocorreu um erro no cadastro do animal.",
                status: "error",
                duration: 3000,
                isClosable: true
            })
        })
    }

    return (
        <>
            <Topbar />
            <main>
                <Box m="1rem">
                    <Box mb="1rem">
                        <Heading color="gray.700">Registro de Animal</Heading>
                        <Heading as="h3" size="sm" color="gray.500" fontWeight="light">Registre um novo animal inserindo as informações abaixo.</Heading>
                    </Box>
                    <form method="post" onSubmit={(event) => handleAnimalRegistration(event)}>
                        <FormControl isRequired>
                            <FormLabel htmlFor="numeroMae" fontWeight="bold">Número da Mãe</FormLabel>
                            <NumberInput min={1}>
                                <NumberInputField id="numeroMae" onInput={(event) => setMotherNumber(event.target.value)} />
                                <NumberInputStepper>
                                    <NumberIncrementStepper />
                                    <NumberDecrementStepper />
                                </NumberInputStepper>
                            </NumberInput>
                            <FormHelperText>O número da mãe do animal.</FormHelperText>
                        </FormControl>

                        <FormControl isRequired>
                            <FormLabel htmlFor="sexo" fontWeight="bold">Sexo</FormLabel>
                            <RadioGroup defaultValue="M" id="sexo" onChange={setSex} value={sex}>
                                <HStack spacing="24px">
                                    <Radio value="M">Masculino</Radio>
                                    <Radio value="F">Feminino</Radio>
                                </HStack>
                            </RadioGroup>
                            <FormHelperText>O sexo do animal.</FormHelperText>
                        </FormControl>

                        <FormControl isRequired>
                            <FormLabel htmlFor="nascimento" fontWeight="bold">Data nascimento</FormLabel>
                            <Input type="date" id="nascimento" onInput={(event) => setBirthDate(event.target.value)} />
                            <FormHelperText>A data de nascimento do animal.</FormHelperText>
                        </FormControl>

                        <FormControl isRequired>
                            <FormLabel htmlFor="marca" fontWeight="bold">Marca</FormLabel>
                            <Input type="text" id="marca" onInput={(event) => setBrand(event.target.value)} />
                            <FormHelperText>A marca do animal.</FormHelperText>
                        </FormControl>

                        <ButtonGroup>
                            <Button
                                colorScheme="blue"
                                rightIcon={<ArrowLeft size={23} weight="bold" />}
                                as={RouterLink}
                                to="/animais">
                                Voltar
                            </Button>
                            <Button
                                colorScheme="green"
                                rightIcon={<FloppyDisk size={23} />}
                                type="submit">
                                Registrar
                            </Button>
                        </ButtonGroup>
                    </form>
                </Box>
            </main>
        </>
    )
}

export default RegisterAnimal