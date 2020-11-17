import React, { useState, useEffect } from 'react';
import { Bar } from 'react-chartjs-2';
import { Link, useHistory } from 'react-router-dom';
import UserImg from '../../assets/user.png';
import '../../styles/StudentsChart.css';

const InstitutionsChart = () => {
    const [chartData, setChartData] = useState({});
    const [chartOptions, setChartOptions] = useState({});

    function chart() {
        setChartData({
            labels: ['Média da sua instituição', 'Média das outras instituições'],
            datasets: [{
                label: 'Nota ',
                data: [8, 7],
                backgroundColor: [
                    'rgba(255, 99, 132, 0.2)',
                    'rgba(54, 162, 235, 0.2)',
                ],
                borderColor: [
                    'rgba(255, 99, 132, 1)',
                    'rgba(54, 162, 235, 1)',
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

    useEffect(()=>{
        chart();
    },[]);

    return (
        <div id="chart-container">
            <div className="side-menu">
                <div className="side-container">
                    <img alt="user" src={UserImg} />
                    <h2>Bem vindo </h2>
                    <Link to="/home">Home</Link>
                    <Link to="/subjects-institution">Escolher matérias</Link>
                    <Link to="/subjects-institution-edit">Editar matérias escolhidas</Link>
                    <Link to="/edit-institution">Editar conta</Link>
                    <button  className="button-custom">Logoff</button>
                </div>
            </div>
            <div className="bar">
            <h3>Escolha a matéria</h3>
                <select>
                    <option>Teste</option>
                    <option>Teste2</option>
                </select>
                <Bar height={600} width={800} data={chartData} options={chartOptions}/>
            </div>
            
        </div>
    )
}

export default InstitutionsChart;