import { BrowserRouter, Route, Routes } from "react-router-dom"
import AnimalsGeneral from "./pages/AnimalsGeneral"
import { AnimalsSuckling } from "./pages/AnimalsSuckling"
import { RegisterAnimal } from "./pages/RegisterAnimal"
import { Metrics } from "./pages/Metrics"

import '@fontsource/eb-garamond/400.css'
import '@fontsource/roboto/400.css'
import { ChakraProvider } from "@chakra-ui/react"
import registroFazendaTheme from "./chakra.config"

function App() {
  return (
    <ChakraProvider theme={registroFazendaTheme}>
      <BrowserRouter>
        <Routes>
          <Route path="/animais/mamando" element={<AnimalsSuckling />} />
          <Route path="/animais" element={<AnimalsGeneral />} />
          <Route path="/animais/metricas" element={<Metrics />} />
          <Route path="/animais/cadastrar" element={<RegisterAnimal />} />
        </Routes>
      </BrowserRouter>
    </ChakraProvider>
  )
}

export default App
