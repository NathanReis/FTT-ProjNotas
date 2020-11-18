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
import { useToasts } from 'react-toast-notifications'

interface User {
    id: number,
    userName: String,
    password: String,
    type: String,
    teachingInstitution: {
        name: String,
        id: number,
    }
}

interface Subject {
    id: number,
    description: string
}

interface SubjectInstitution {
    subject: Subject,
    active: string,
}

const Subjects = () => {
    const [user, setUser] = useState<User>();
    const [filter, setFilter] = useState<string>("");
    const [page, setPage] = useState<number>(1);
    const [subjects, setSubjects] = useState<SubjectInstitution[]>([]);
    const [selectedSubjects, setSelectedSubjects] = useState<number[]>([]);

    const history = useHistory();
    const { addToast } = useToasts();

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

        loadSubjects(1);

    }, []);

    function loadSubjects(pageNumber: number) {
        const userJson = localStorage.getItem('@FTT:user');
        let parsedUser;
        if (userJson) {
            parsedUser = JSON.parse(userJson);
        }
        api.get(`subjects-institution/${parsedUser?.teachingInstitution.id}?page=${pageNumber}&qtd=10`).then(response => {
            setSubjects(response.data);
        });
    }

    function loadSubjectsFilter() {
        const userJson = localStorage.getItem('@FTT:user');
        let parsedUser;
        if (userJson) {
            parsedUser = JSON.parse(userJson);
        }
        api.get(`subjects-institution/${parsedUser?.teachingInstitution.id}?description=${filter}&qtd=10`).then(response => {
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

    function prevPage() {
        if (page === 1) return;
        const pageNumber = page - 1;
        setPage(pageNumber);
        loadSubjects(pageNumber);
    }
    function nextPage() {
        const pageNumber = page + 1;
        setPage(pageNumber);
        loadSubjects(pageNumber)
    }

    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        const { name, value } = event.target;

        setFilter(value);
    }
    function addMessageToast() {
        addToast('Matérias salvas com sucesso!', {
            appearance: "success",
            autoDismiss: true,
        })
    }

    function saveSubjects() {

        const arraySubjects: { subject: { id: number; }; }[] = [];
        selectedSubjects.forEach(s => {
            arraySubjects.push({
                subject: {
                    id: s,
                }
            });
        });

        const data = {
            id: user?.id,
            teachingInstitution: {
                id: user?.teachingInstitution.id,
            },
            subjects: arraySubjects,
        }

        console.log(data);
        api.post('subjectXUser', data);
        addMessageToast();
        history.push('/home');
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
                                subj.active === "A" &&
                                <tr key={subj.subject.id} >
                                    <td><input onChange={() => handleChange(subj.subject.id)} type="checkbox"></input></td>
                                    <td >{subj.subject.description}</td>
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
                    <button onClick={prevPage} disabled={page === 1} className="btn btn-info">Página anterior</button>
                    <button onClick={nextPage} className="btn btn-info">Próxima página</button>
                </div>
            </div>
        </div>
    )
}

export default Subjects;