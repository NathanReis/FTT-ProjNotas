import React, { useState, useEffect } from 'react';
import '../../styles/Home.css'
import User from '../../assets/user.png';
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

const Home = () => {
    const [user, setUser] = useState<User>();
    const history = useHistory();

   

    useEffect(() => {
        const user = localStorage.getItem('@FTT:user');

        if (user) {
            const parsedUser = JSON.parse(user);
            setUser(parsedUser);
            if(parsedUser.type === "I"){
                history.push('/home-institution')
            }
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
                    <img alt="user" src={User} />
                    <h3>Bem vindo {user?.userName}</h3>
                    <Link to="/subjects">Escolher matérias</Link>
                    <Link to="/student-grades">Cadastrar notas</Link>
                    <Link to="/student-chart">Ver médias</Link>
                    <Link to="/edit-student">Editar conta</Link>
                    <button onClick={handleLogoff} className="button-custom">Logoff</button>
                </div>
            </div>
            <div className="home-container">
                <h1>Projeto notas</h1>
                <p>(precisamos pensar em um nome melhor)</p>
                <div className="image-container">
                    <img src={HomeImage} />
                    <ul>
                        <li>Escolha suas matérias que está cursando na faculdade</li>
                        <li>Informe sua média nessa matéria</li>
                        <li>Veja como está se saindo em relação à sua turma (anonimamente)</li>
                        <li>Veja como está se saindo em relação à outras instituições (anonimamente)</li>
                    </ul>
                </div>
            </div>
        </div>
    )
}

export default Home;