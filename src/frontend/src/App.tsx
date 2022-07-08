import { BrowserRouter, Route, Routes } from "react-router-dom"
import AnimalsGeneral from "./pages/AnimalsGeneral"
import { AnimalsSuckling } from "./pages/AnimalsSuckling"
import { Metrics } from "./pages/Metrics"
import RegisterAnimal from "./pages/RegisterAnimal"

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/animais/mamando" element={<AnimalsSuckling />} />
        <Route path="/animais" element={<AnimalsGeneral />} />
        <Route path="/animais/metricas" element={<Metrics />} />
        <Route path="/animais/cadastrar" element={<RegisterAnimal />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
