import React from 'react';
import '../../styles/Subjects.css';
import Table from 'react-bootstrap/Table'
import 'bootstrap/dist/css/bootstrap.min.css';
import Col from 'react-bootstrap/esm/Col';
import { Link } from 'react-router-dom';
import User from '../../assets/user.png';

const Subjects = () => {
    return (
        <div id="subjects-container">
            <div className="side-menu">
                <div className="side-container">
                    <img alt="user" src={User} />
                    <h4>Bem vindo usuário</h4>
                    <Link to="/home">Home</Link>
                    <Link to="">Editar conta</Link>
                    <br></br>
                    <button className="btn btn-info">Salvar matérias</button>
                </div>
            </div>
            <div className="main-container">
                <h1>Matérias</h1>
                <p>Escolha suas matérias a seguir para poder cadastrar e visualizar sua colocação</p>
                <Col >
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Selecionar</th>
                                <th>Matéria</th>
                                <th>Semestre</th>
                                <th>Curso</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td><input type="checkbox"></input></td>
                                <td>Cálculo 1</td>
                                <td>Semestre 1</td>
                                <td>EC</td>
                            </tr>
                            <tr>
                                <td><input type="checkbox"></input></td>
                                <td>Cálculo 2</td>
                                <td>Semestre 2</td>
                                <td>EC</td>
                            </tr>
                            <tr>
                                <td><input type="checkbox"></input></td>
                                <td>Linguagem de programação 3</td>
                                <td>Semestre 7</td>
                                <td>EC</td>
                            </tr>
                        </tbody>
                    </Table>
                </Col>
            </div>
        </div>
    )
}

export default Subjects;