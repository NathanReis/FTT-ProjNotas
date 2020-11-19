import React, { useState, useEffect, ChangeEvent } from 'react';
import { Bar } from 'react-chartjs-2';
import { Link, useHistory } from 'react-router-dom';
import UserImg from '../../assets/user.png';
import Logoff from '../../helpers/Logoff';
import api from '../../services/api';
import '../../styles/StudentsChart.css';


interface SubjectsUser {
    subject: {
        description: string,
        id: number,
    },
    grade: number,
    semester: number,
    year: number
}

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

interface Grades{
    grade:number,
    averageInstitution:number,
    averageOther:number,
}

const StudentsChart = () => {
    const [userSubjects, setUserSubjects] = useState<SubjectsUser[]>([]);
    const [user, setUser] = useState<User>();
    const [selectedSubject, setSelectedSubject] = useState(0);
    const [chartData, setChartData] = useState({});
    const [chartOptions, setChartOptions] = useState({});

    const history = useHistory();

    

    function chart(grades:Grades) {
        const array = [grades.grade, grades.averageInstitution, grades.averageOther];
        setChartData({
            labels: ['Sua média', 'Média da sua instituição', 'Média das outras instituições'],
            datasets: [{
                label: 'Nota ',
                data: array,
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                    'rgba(255, 206, 86, 0.2)',
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
                    'rgba(255, 206, 86, 1)',
                ],
                borderWidth: 1
            }]
        });

        setChartOptions({
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        })

    }
    function handleLogoff() {
        Logoff();
        history.push('/');
    }

    async function handleSelectSubject(event: ChangeEvent<HTMLSelectElement>) {
        const idSubj = event.target.value;
        setSelectedSubject(Number(idSubj));

        const data = {
            idUser: user?.id,
            idSubject: idSubj,
            idTeachingInstitution: user?.teachingInstitution.id,
            semester: 2,
            year: 2020,
        }
        
        const resp = await api.post('/GraphicSearchUserAPI',data);
        chart(resp.data);
    }
    useEffect(() => {
        // chart();
        const user = localStorage.getItem('@FTT:user');
        let parsedUser;
        if (user) {
            parsedUser = JSON.parse(user);
            setUser(parsedUser);
        }
        else {
            history.push('/');
            return;
        }

        api.get(`subjects-user/${parsedUser?.id}`).then(response => {
            setUserSubjects(response.data);
            console.log(response.data)
        });

    }, []);

    return (
        <div id="chart-container">
            <div className="side-menu">
                <div className="side-container">
                    <img alt="user" src={UserImg} />
                    <h2>Bem vindo </h2>
                    <Link to="/home">Home</Link>
                    <Link to="/subjects">Escolher matérias</Link>
                    <Link to="/student-grades">Cadastrar notas</Link>
                    <Link to="/edit-student">Editar conta</Link>
                    <button onClick={handleLogoff} className="button-custom">Logoff</button>
                </div>
            </div>
            <div className="bar">
                <div className="selects">
                    <select name="subjects" id="subjects" value={selectedSubject} onChange={handleSelectSubject} className="select">
                        <option value="0">Escolha a matéria</option>
                        {userSubjects.map(subj => (
                            <option key={subj.subject.id} value={subj.subject.id} >{subj.subject.description}</option>
                        ))}
                    </select>
                    <select name="subjects" id="subjects"  className="select">
                        {/* <option value="0">Escolha o semestre</option> */}
                        <option value="2">2</option>
                        
                    </select>
                    <select name="subjects" id="subjects"  className="select">
                        {/* <option value="0">Escolha o ano</option> */}
                        <option value="2020">2020</option>
                    </select>
                </div>

                <Bar height={600} width={800} data={chartData} options={chartOptions} />
            </div>

        </div>
    )
}

export default StudentsChart;