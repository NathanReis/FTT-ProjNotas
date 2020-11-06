import React, { useState, ChangeEvent, FormEvent } from 'react';
import '../../styles/SignIn.css';
import { Link, useHistory } from 'react-router-dom';
import { GiExitDoor } from "react-icons/gi";
import { ToastProvider, useToasts } from 'react-toast-notifications'
import Logo2 from '../../assets/logo2.jpg';
import Chart from '../../assets/chart2.png';
import api from '../../services/api';


interface loginResponse {
    userName: String,
    password: String,
    type: String,
    teachingInstitution: {
        name: String;
    }
}

const SignIn = () => {
    const { addToast } = useToasts();
    const history = useHistory();

    const [formData, setFormData] = useState({
        userName: '',
        password: '',
    });

    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        const { name, value } = event.target;

        setFormData({ ...formData, [name]: value });
    }

    function addMessageToast() {
        addToast('Login efetuado com sucesso!', {
            appearance: "success",
            autoDismiss: true,
        })
    }

    function addMessageToastError() {
        addToast('Erro ao logar, verifique seu usuário e senha.', {
            appearance: 'error',
            autoDismiss: true,
        })
    }

    async function handleSubmit(event: FormEvent) {
        try {
            event.preventDefault();

            const { userName, password } = formData;

            const data = {
                userName,
                password,
            }

            const response = await api.post<loginResponse>('access', data);
            const user = response.data;

            if (user) {
                addMessageToast();
                localStorage.setItem('@FTT:user',JSON.stringify(user));
                if (user.type === 'A') {
                    history.push('/home');
                    return;
                }
                history.push('/')
                return;
            }
            addMessageToastError();
        } catch (error) {
            addMessageToastError();
        }

    }



    return (
        <div id="home-container">
            <div className="login-container">
                <img src={Chart} />
                <h1>Login</h1>
                <form onSubmit={handleSubmit}>
                    <input name="userName" id="userName" onChange={handleInputChange} placeholder="Digite seu login"></input>
                    <input type="password" name="password" id="password" onChange={handleInputChange} placeholder="Digite sua senha"></input>
                    <button type="submit">Entrar</button>
                </form>

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