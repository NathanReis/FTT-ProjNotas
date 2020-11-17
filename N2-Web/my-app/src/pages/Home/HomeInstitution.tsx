import React, { useState, useEffect } from 'react';
import '../../styles/Home.css'
import UserImg from '../../assets/user.png';
import HomeImage from '../../assets/logo.svg';
import { Link, useHistory } from 'react-router-dom';
import LoggedUser from '../../helpers/LoggedUser';
import Logoff from '../../helpers/Logoff';

interface User {
    userName: String,
    password: String,
    type: String,
    teachingInstitution: {
        name: String;
    }
}

const HomeInstitution = () => {
    const [user, setUser] = useState<User>();
    const history = useHistory();

    // useEffect(() => {
    //     const user = LoggedUser;
    //     setUser(user);
    // }, []);

    useEffect(() => {
        const user = localStorage.getItem('@FTT:user');

        if (user) {
            const parsedUser = JSON.parse(user);
            setUser(parsedUser);
        }
        else{
            history.push('/');
            return;
        }
    }, []);

    function handleLogoff() {
        Logoff();
        history.push('/');
    }

    return (
        <div id="homepage-container">

            <div className="side-menu">
                <div className="side-container">
                    <img alt="user" src={UserImg} />
                    <h2>Bem vindo {user?.userName}</h2>
                    <Link to="/subjects-institution">Escolher matérias</Link>
                    <Link to="/subjects-institution-edit">Editar matérias escolhidas</Link>
                    <Link to="/institution-chart">Ver médias dos alunos</Link>
                    <Link to="/edit-institution">Editar conta</Link>
                    <button onClick={handleLogoff} className="button-custom">Logoff</button>
                </div>
            </div>
            <div className="home-container">
                <h1>Projeto notas</h1>
                <p>(precisamos pensar em um nome melhor)</p>
                <div className="image-container">
                    <img src={HomeImage} />
                    <ul>
                        <li>Escolha as matérias que façam parte da sua instituição</li>
                        <li>Veja como seus alunos estão em relação a outras instituições</li>
                    </ul>
                </div>
            </div>
        </div>
    )
}

export default HomeInstitution;