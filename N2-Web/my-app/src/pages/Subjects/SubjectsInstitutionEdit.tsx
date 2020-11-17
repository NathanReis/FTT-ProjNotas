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
        name: String,
        id: number,
    }
    id: number;
}

interface Subject {
    id: number,
    description: string
}

interface SubjectInstitution {
    subject: Subject,
    active: string,
}

const SubjectsInstitutionEdit = () => {
    const [user, setUser] = useState<User>();
    const [filter, setFilter] = useState<string>("");
    const [page, setPage] = useState<number>(1);
    const [subjects, setSubjects] = useState<SubjectInstitution[]>([]);
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

        loadSubjects(1);

    }, []);

    function loadSubjects(pageNumber: number) {
        const userJson = localStorage.getItem('@FTT:user');
        let parsedUser;
        if (userJson) {
            parsedUser = JSON.parse(userJson);
        }
        api.get(`subjects-institution/${parsedUser?.teachingInstitution.id}?page=${pageNumber}&qtd=10&idInstitution=${user?.id}`).then(response => {
            setSubjects(response.data);
            console.log(page)
        });
    }

    function loadSubjectsFilter() {
        const userJson = localStorage.getItem('@FTT:user');
        let parsedUser;
        if (userJson) {
            parsedUser = JSON.parse(userJson);
        }
        api.get(`subjects-institution/${parsedUser?.teachingInstitution.id}?description=${filter}&qtd=10&idInstitution=${user?.id}`).then(response => {
            setSubjects(response.data);
        });
    }

    function handleLogoff() {
        Logoff();
        history.push('/');
    }

    function handleChange(active: string, id: number) {
        let act = "";
        if (active === "A") {
            act = "I"
        }
        else {
            act = "A"
        }

        const data = {
            idTeachingInstitution: user?.teachingInstitution.id,
            idSubject: id,
            active: act
        }

        api.put('toggle-active-subject', data);
        window.location.reload();
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

    function handleCheckBoxChange(event: ChangeEvent<HTMLInputElement>) {
        const { name, value } = event.target;

        console.log(event.target);
    }

    function saveSubjects() {
        // if(selectedSubjects.length === 0){
        //     history.push('/home');
        //     return;
        // }

        // const subjs: { subject: { id: number; description: string; }; active: string; }[] =[];

        // selectedSubjects.forEach(s => {
        //     subjs.push({
        //         subject: {
        //             id: s,
        //             description: "",
        //         }  ,
        //         active: "",              
        //     });
        // });

        // const data = {
        //     id: user?.id,
        //     name: user?.userName,
        //     subjects: subjs,
        // };

        // console.log(data);


        console.log(subjects);
    }

    return (
        <div id="subjects-container">
            <div className="side-menu">
                <div className="side-container">
                    <img alt="user" src={User} />
                    <h4>Bem vindo {user?.userName}</h4>
                    <Link to="/home">Home</Link>
                    <Link to="/institution-chart">Ver médias</Link>
                    <Link to="/edit-institution">Editar conta</Link>
                    <button onClick={handleLogoff} className="button-custom">Logoff</button>
                    <br></br>
                    <button onClick={saveSubjects} className="btn btn-info">Salvar matérias</button>
                </div>
            </div>
            <div className="main-container">
                <h1>Matérias</h1>
                <p>Gerencia as matérias da sua instituição a seguir</p>
                <Col >
                    <p>Pesquisar matéria</p>
                    <div className="filter-container">
                        <input onChange={handleInputChange} type="text" />
                        <button onClick={loadSubjectsFilter} className="btn btn-info">Buscar</button>
                    </div>
                    <Table striped bordered hover>
                        <thead>
                            <tr>
                                <th>Ativar/Desativar</th>
                                <th>Matéria</th>

                            </tr>
                        </thead>
                        <tbody>

                            {subjects.map(subj =>
                                <tr key={subj.subject.id}>
                                    <td><input checked={subj.active === "A" ? true : false} onChange={() => handleChange(subj.active, subj.subject.id)} type="checkbox"></input></td>
                                    <td key={subj.subject.id} >{subj.subject.description}</td>
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

export default SubjectsInstitutionEdit;