import React, { useEffect, useState, ChangeEvent } from 'react';
import '../../styles/Subjects.css';
import Table from 'react-bootstrap/Table'
import 'bootstrap/dist/css/bootstrap.min.css';
import Col from 'react-bootstrap/esm/Col';
import { Link, useHistory } from 'react-router-dom';
import User from '../../assets/user.png';
import LoggedUser from '../../helpers/LoggedUser';
import Logoff from '../../helpers/Logoff';
import api from '../../services/api';

interface User {
    userName: String,
    password: String,
    type: String,
    teachingInstitution: {
        name: String;
    }
}

interface Subject {
    id: number,
    description: string
}

const Subjects = () => {
    const [user, setUser] = useState<User>();
    const [filter, setFilter] = useState<string>("");
    const [page, setPage] = useState<number>(1);
    const [subjects, setSubjects] = useState<Subject[]>([]);
    const [selectedSubjects, setSelectedSubjects] = useState<number[]>([]);
    const history = useHistory();

    // const subjects = ['Fisica', 'Calculo', 'Algoritmos', 'LP']

    useEffect(() => {
        const user = localStorage.getItem('@FTT:user');

        if (user) {
            const parsedUser = JSON.parse(user);
            setUser(parsedUser);
        }
        else {
            history.push('/');
            return;
        }

        loadSubjects();

    }, []);

    function loadSubjects() {
        api.get(`subject-institution-filter?page=${page}&qtd=10`).then(response => {
            setSubjects(response.data);
        });
    }

    function loadSubjectsFilter() {
        api.get(`subject-institution-filter?description=${filter}&qtd=10`).then(response => {
            setSubjects(response.data);
        });
    }

    function handleLogoff() {
        Logoff();
        history.push('/');
    }

    function handleChange(id: number) {

        const findedId = selectedSubjects.findIndex(subjId => subjId === id);
        if (findedId < 0) {
            setSelectedSubjects(selectedSubjects => [...selectedSubjects, id]);

        }
        else {
            setSelectedSubjects(selectedSubjects.filter(subjId => subjId !== id));
        }

    }

    function prevPage(){
        if (page === 1) return;
        const pageNumber = page - 1;
        setPage(pageNumber);
        loadSubjects();
    }
    function nextPage(){
        const pageNumber = page + 1;
        setPage(pageNumber);
        loadSubjects()
    }

    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        const { name, value } = event.target;

        setFilter(value);
    }

    function saveSubjects() {
        console.log(selectedSubjects);
    }

    return (
        <div id="subjects-container">
            <div className="side-menu">
                <div className="side-container">
                    <img alt="user" src={User} />
                    <h4>Bem vindo {user?.userName}</h4>
                    <Link to="/home">Home</Link>
                    <Link to="/student-grades">Cadastrar notas</Link>
                    <Link to="/student-chart">Ver médias</Link>
                    <Link to="/edit-student">Editar conta</Link>
                    <button onClick={handleLogoff} className="button-custom">Logoff</button>
                    <br></br>
                    <button onClick={saveSubjects} className="btn btn-info">Salvar matérias</button>
                </div>
            </div>
            <div className="main-container">
                <h1>Matérias</h1>
                <p>Escolha suas matérias a seguir para poder cadastrar e visualizar sua colocação</p>
                <Col >
                    <p>Pesquisar matéria</p>
                    <div className="filter-container">
                        <input onChange={handleInputChange} type="text" />
                        <button onClick={loadSubjectsFilter} className="btn btn-info">Buscar</button>
                    </div>
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Selecionar</th>
                                <th>Matéria</th>

                            </tr>
                        </thead>
                        <tbody>

                            {subjects.map(subj =>
                                <tr>
                                    <td><input onChange={() => handleChange(subj.id)} type="checkbox"></input></td>
                                    <td key={subj.id} >{subj.description}</td>
                                </tr>)}

                            {/* <tr>
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
                            </tr> */}
                        </tbody>
                    </Table>
                </Col>
                <div className="page-container">
                    <button onClick={prevPage} disabled={page===1} className="btn btn-info">Página anterior</button>
                    <button onClick={nextPage} className="btn btn-info">Próxima página</button>
                </div>
            </div>
        </div>
    )
}

export default Subjects;