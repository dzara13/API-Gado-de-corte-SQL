import CowPng from '../../assets/cow-head-icon.png'
import { Box, Button, Image, Menu, MenuButton, MenuItem, MenuList } from '@chakra-ui/react'
import { Link as RouterLink } from 'react-router-dom'
import './topbar.css'
import '../../global.css'
import { CaretDown } from 'phosphor-react'

export function Topbar() {
    return (
        <header>
            <Box id="topbar" boxShadow="md">
                <span id="logo">
                    <Image src={CowPng} alt="Vaquinha" boxSize="3rem" />
                    <h1>REGISTRO FAZENDA</h1>
                </span>
                <nav>
                    <Menu>
                        <MenuButton as={Button} variant="link" rightIcon={<CaretDown />} colorScheme="white" >
                            Gerenciar Animais
                        </MenuButton>

                        <MenuList color="black">
                            <MenuItem as={RouterLink} to="/animais">
                                Listagem geral
                            </MenuItem>

                            <MenuItem as={RouterLink} to="/animais/mamando">
                                Listagem de não-desmamados
                            </MenuItem>

                            <MenuItem as={RouterLink} to="/animais/cadastrar">
                                Registro
                            </MenuItem>

                            <MenuItem as={RouterLink} to="/animais/metricas">
                                Métricas
                            </MenuItem>
                        </MenuList>
                    </Menu>
                </nav>
            </Box >
        </header>
    )
}

export default Topbar