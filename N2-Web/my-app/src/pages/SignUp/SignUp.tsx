import React, { useState, ChangeEvent, FormEvent, useEffect } from 'react';
import '../../styles/SignUp.css';
import { Link, useHistory } from 'react-router-dom';
import { FiArrowLeft } from "react-icons/fi";
import { ToastProvider, useToasts } from 'react-toast-notifications'
import api from '../../services/api';
import * as Yup from 'yup';

import Logo from '../../assets/chart3.jpg';
import Chart from '../../assets/chart2.png';

interface institution{
    name:String,
    id:number,
}


const SignUp = () => {
    const { addToast } = useToasts();
    const history = useHistory();
    const [institutions, setInstitutions] = useState<institution[]>([]);
    const [selectedInstitution, setSelectedInstitution] = useState("0");

    useEffect(() => {
        api.get('teaching-institution').then(response => {
            setInstitutions(response.data);
        })
    }, []);

    function addMessageToast() {
        addToast('Cadastrado com sucesso.', {
            appearance: 'success',
            autoDismiss: true,
        })
    }

    function addMessageToastError(message: String) {
        addToast(message, {
            appearance: 'error',
            autoDismiss: true,
        })
    }

    const [formData, setFormData] = useState({
        userName: '',
        password: '',
    });

    function handleSelectInstitution(event: ChangeEvent<HTMLSelectElement>) {
        const inst = event.target.value;
        setSelectedInstitution(inst);
    }

    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        const { name, value } = event.target;

        setFormData({ ...formData, [name]: value });
    }

    async function validateData(data: object) {
        const schema = Yup.object().shape({
            userName: Yup.string().required('Nome obrigatório'),
            teachingInstitution : Yup.object().shape( {
                id: Yup.number().min(1),
            }),
            password: Yup.string().min(3, 'No mínimo 3 caracteres'),
        });

        await schema.validate(data, {
            abortEarly: false,
        });
    }

    async function handleSubmit(event: FormEvent) {
        try {
            event.preventDefault();

            const { userName, password } = formData;
            const inst = Number(selectedInstitution);
            const tipo = 'A';


            const data = {
                userName,
                password,
                teachingInstitution: {
                    id: inst
                },
                type: tipo
            }

            await validateData(data);

            const response = await api.post('user', data);

            if (response.data.hasError === true) {
                addMessageToastError(response.data.messageError);
                return;
            }
            addMessageToast();
            history.push('/');
        } catch (error) {
            addToast('Erro ao se cadastrar.', {
                appearance: 'error',
                autoDismiss: true,
            })
        }

    }


    return (
        <div id="pagesignup-container">

            <div className="logo-container">
                <img src={Logo}></img>
            </div>
            <div className="signup-container">
                <img src={Chart} />
                <form onSubmit={handleSubmit}>
                    <h1>Cadastro</h1>
                    <input name="userName" id="userName" onChange={handleInputChange} className="input" placeholder="Digite seu login"></input>
                    <input type="password" name="password" id="password" onChange={handleInputChange} className="input" placeholder="Digite sua senha"></input>
                    
                    <select name="institution" id="institution" value={selectedInstitution} onChange={handleSelectInstitution} className="select">
                        <option value="0">Escolha sua faculdade</option>
                        {institutions.map(inst => (
                            <option key={inst.id} value={inst.id} >{inst.name}</option>
                        ))}
                    </select>
                    <button type="submit">Cadastrar</button>
                </form>
                <Link to="/signupInstitution">Se cadastrar como instituição</Link>
                <div className="back-home">
                    <FiArrowLeft />
                    <Link to="">Voltar para home</Link>
                </div>
            </div>
        </div>
    )
}

export default SignUp;