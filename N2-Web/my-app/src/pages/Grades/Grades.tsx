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
import { ToastProvider, useToasts } from 'react-toast-notifications'
import * as Yup from 'yup';

interface User {
    id: number,
    userName: String,
    password: String,
    type: String,
    teachingInstitution: {
        id:number,
        name: String;
    }
}

interface Grades {
    idSubject: number,
    grade: number
}

interface Subject {
    subject: {
        description: string,
        id: number,
    },
    grade: number,
    semester: number,
    year: number,
}

const Grades = () => {
    const { addToast } = useToasts();

    const [user, setUser] = useState<User>();
    const [filter, setFilter] = useState<string>("");
    const [page, setPage] = useState<number>(1);
    const [subjects, setSubjects] = useState<Subject[]>([]);
    const [subjectsGrade, setSubjectsGrade] = useState<Grades[]>([]);
    const history = useHistory();

    const subjects2 = ['Fisica', 'Calculo', 'Algoritmos', 'LP']

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
        api.get(`subjects-user/${parsedUser?.id}?page=${pageNumber}&qtd=10`).then(response => {
            setSubjects(response.data);
        });
    }

    function loadSubjectsFilter() {
        const userJson = localStorage.getItem('@FTT:user');
        let parsedUser;
        if (userJson) {
            parsedUser = JSON.parse(userJson);
        }
        api.get(`subjects-user/${parsedUser?.id}?description=${filter}&qtd=10`).then(response => {
            setSubjects(response.data);
        });
    }

    function handleLogoff() {
        Logoff();
        history.push('/');
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

    async function handleInputGradeChange(event: ChangeEvent<HTMLInputElement>) {
        const { name, value } = event.target;

        const data: Grades = {
            idSubject: Number(name),
            grade: Number(value)
        }

        const findedId = subjectsGrade.findIndex(subjId => subjId.idSubject === Number(name));
        if (findedId >= 0) {
            setSubjectsGrade(subjectsGrade.filter(subjId => subjId.idSubject !== Number(name)));

        }
        setSubjectsGrade(subjectsGrade => [...subjectsGrade, data]);
    }

    async function saveSubjects() {
        try {
            const data = {
                idUser: user?.id,
                idTeachingInstitution: user?.teachingInstitution.id,
                subjectGrade: subjectsGrade,
            }
            let hasError:Boolean = false;
            data.subjectGrade.map(async grade => {
                if(grade.grade < 0 || grade.grade > 10){
                    addMessageToastError("Nota deve estar entre 0 e 10");
                    hasError = true;
                    return;
                }
            });
            if(hasError){
                return;
            }
            const resp = await api.put('GradeAPI', data);

            addMessageToast();

            history.push('/home');
        } catch (error) {
            addMessageToastError(error.message);
        }
    }

    function addMessageToast() {
        addToast('Notas salvas com sucesso!', {
            appearance: "success",
            autoDismiss: true,
        })
    }
    function addMessageToastError(message: string) {
        addToast(message, {
            appearance: "error",
            autoDismiss: true,
        })
    }

    return (
        <div id="subjects-container">
            <div className="side-menu">
                <div className="side-container">
                    <img alt="user" src={User} />
                    <h4>Bem vindo {user?.userName}</h4>
                    <Link to="/home">Home</Link>
                    <Link to="/subjects">Escolher matérias</Link>
                    <Link to="/student-chart">Ver médias</Link>
                    <Link to="/edit-student">Editar conta</Link>
                    <button onClick={handleLogoff} className="button-custom">Logoff</button>
                    <br></br>
                    <button onClick={saveSubjects} className="btn btn-info">Salvar notas</button>
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
                                <th>Matéria</th>
                                <th>Informe sua nota (média)</th>
                            </tr>
                        </thead>
                        <tbody>
                            {subjects.map(subj =>
                                <tr>
                                    <td>{subj.subject.description}</td>
                                    <td><input name={subj.subject.id.toString()} placeholder={subj.grade.toString()} onChange={handleInputGradeChange} type="number" min={0} max={10} /></td>
                                </tr>)}
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

export default Grades;