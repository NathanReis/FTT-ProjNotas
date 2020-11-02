import React from 'react';
import '../../styles/Home.css'
import User from '../../assets/user.png';
import HomeImage from '../../assets/logo.svg';
import { Link } from 'react-router-dom';

const Home = () => {
    return (
        <div id="homepage-container">

            <div className="side-menu">
                <div className="side-container">
                    <img alt="user" src={User} />
                    <h2>Bem vindo usuário</h2>
                    <Link to="/subjects">Escolher matérias</Link>
                    <Link to="">Cadastrar notas</Link>
                    <Link to="">Ver médias</Link>
                    <Link to="">Editar conta</Link>
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