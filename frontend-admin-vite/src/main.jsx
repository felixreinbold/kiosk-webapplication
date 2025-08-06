import { StrictMode } from 'react'
import { createRoot } from 'react-dom/client'
import './index.css'
import './styles/login.css'
import './styles/Dashboard.css'
import './styles/AngebotForm.css'
import './styles/ChangePasswordForm.css'
import App from './App.jsx'

createRoot(document.getElementById('root')).render(
  <StrictMode>
    <App />
  </StrictMode>,
)
