import CowPng from '../../assets/cow-head-icon.png'
import { Box, Image, Link } from '@chakra-ui/react'
import { Link as RouterLink } from 'react-router-dom'
import './topbar.css'
import '../../global.css'

export function Topbar() {
    return (
        <Box id="topbar" boxShadow="md">
            <span id="logo">
                <Image src={CowPng} alt="Vaquinha" boxSize="3rem" />
                <h1>REGISTRO FAZENDA</h1>
            </span>
            <nav>
                <Link as={RouterLink} to="/animais">Listagem geral de animais</Link>
                <Link as={RouterLink} to="/animais/mamando">Listagem de animais mamando</Link>
                <Link as={RouterLink} to="/animais/cadastrar">Registro de animal</Link>
                <Link as={RouterLink} to="/metricas">Métricas</Link>
            </nav>
        </Box>
    )
}

export default Topbar