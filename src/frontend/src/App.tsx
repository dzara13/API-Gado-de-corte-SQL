import { BrowserRouter, Route, Routes } from "react-router-dom"
import { Home } from "./pages/Home"
import { Metrics } from "./pages/Metrics"
import RegisterAnimal from "./pages/RegisterAnimal"

function App() {
  return (
    <BrowserRouter>
      <Routes>
        <Route path="/" element={<Home />} />
        <Route path="/metricas" element={<Metrics />} />
        <Route path="/animais/cadastrar" element={<RegisterAnimal />} />
      </Routes>
    </BrowserRouter>
  )
}

export default App
