import { Box, Button, ButtonGroup, FormControl, FormHelperText, FormLabel, Heading, HStack, Input, NumberDecrementStepper, NumberIncrementStepper, NumberInput, NumberInputField, NumberInputStepper, Radio, RadioGroup } from "@chakra-ui/react"
import { FloppyDisk, X } from "phosphor-react"
import Topbar from "../../components/Topbar"
import { Link as RouterLink } from "react-router-dom"

import "./style.css"

export function RegisterAnimal() {
    return (
        <>
            <Topbar />
            <main>
                <Box m="1rem">
                    <Box mb="1rem">
                        <Heading color="gray.700">Registro de Animal</Heading>
                        <Heading as="h3" size="sm" color="gray.500" fontWeight="light">Registre um novo animal inserindo as informações abaixo.</Heading>
                    </Box>
                    <form method="post" onSubmit={() => null}>
                        <FormControl isRequired>
                            <FormLabel htmlFor="numeroMae" fontWeight="bold">Número da Mãe</FormLabel>
                            <NumberInput min={1}>
                                <NumberInputField id="numeroMae" />
                                <NumberInputStepper>
                                    <NumberIncrementStepper />
                                    <NumberDecrementStepper />
                                </NumberInputStepper>
                            </NumberInput>
                            <FormHelperText>O número da mãe do animal.</FormHelperText>
                        </FormControl>
                        <FormControl isRequired>
                            <FormLabel htmlFor="sexo" fontWeight="bold">Sexo</FormLabel>
                            <RadioGroup defaultValue="M" id="sexo">
                                <HStack spacing="24px">
                                    <Radio value="M">Masculino</Radio>
                                    <Radio value="F">Feminino</Radio>
                                </HStack>
                            </RadioGroup>
                            <FormHelperText>O sexo do animal.</FormHelperText>
                        </FormControl>
                        <FormControl isRequired>
                            <FormLabel htmlFor="nascimento" fontWeight="bold">Data nascimento</FormLabel>
                            <Input type="date" id="nascimento" />
                            <FormHelperText>A data de nascimento do animal.</FormHelperText>
                        </FormControl>
                        <FormControl isRequired>
                            <FormLabel htmlFor="marca" fontWeight="bold">Marca</FormLabel>
                            <Input type="text" id="marca" />
                            <FormHelperText>A marca do animal.</FormHelperText>
                        </FormControl>
                        <ButtonGroup>
                            <Button
                                colorScheme="red"
                                rightIcon={<X size={23} weight="bold" />}
                                as={RouterLink}
                                to="/animais">
                                Cancelar
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