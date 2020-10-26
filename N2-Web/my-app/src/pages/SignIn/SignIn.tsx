import React from 'react';
import '../../styles/SignIn.css';
import { Link } from 'react-router-dom';
import { GiExitDoor } from "react-icons/gi";
import { ToastProvider, useToasts } from 'react-toast-notifications'
import Logo2 from '../../assets/logo2.jpg';
import Chart from '../../assets/chart2.png';

const SignIn = () => {
    const { addToast } = useToasts();
    function addMessageToast(){
        addToast('Login e senha inválidos', {
            appearance: 'error',
            autoDismiss: true,
          })
    }
    return (
        <div id="home-container">
            <div className="login-container">
                <img src={Chart} />
                <h1>Login</h1>
                <input placeholder="Digite seu login"></input>
                <input placeholder="Digite sua senha"></input>
                <button onClick={addMessageToast}>Entrar</button>
                <div className="create-account">
                    <GiExitDoor />
                    <Link to="/signup">Criar conta</Link>
                </div>
            </div>
            <div className="logo-container">
                <img src={Logo2}></img>
            </div>
        </div>
    )
}

export default SignIn;