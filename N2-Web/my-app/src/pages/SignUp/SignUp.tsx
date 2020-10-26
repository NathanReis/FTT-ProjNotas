import React from 'react';
import '../../styles/SignUp.css';
import { Link } from 'react-router-dom';
import { FiArrowLeft } from "react-icons/fi";
import { ToastProvider, useToasts } from 'react-toast-notifications'

import Logo from '../../assets/chart3.jpg';
import Chart from '../../assets/chart2.png';

const SignUp = () => {
    const { addToast } = useToasts();
    function addMessageToast() {
        addToast('Cadastrado com sucesso.', {
            appearance: 'success',
            autoDismiss: true,
        })
    }
    return (
        <div id="pagesignup-container">

            <div className="logo-container">
                <img src={Logo}></img>
            </div>
            <div className="signup-container">
                <img src={Chart} />
                <form>
                    <h1>Cadastro</h1>
                    <input className="input" placeholder="Digite seu nome"></input>
                    <input className="input" placeholder="Digite seu login"></input>
                    <input className="input" placeholder="Digite sua senha"></input>
                    <select className="select">
                        <option value="0">Escolha sua faculdade</option>
                        <option value="1">FTT</option>
                        <option value="2">Fatec</option>
                    </select>
                    <button onClick={addMessageToast}>Cadastrar</button>
                </form>
                <div className="back-home">
                    <FiArrowLeft />
                    <Link to="">Voltar para home</Link>
                </div>
            </div>
        </div>
    )
}

export default SignUp;