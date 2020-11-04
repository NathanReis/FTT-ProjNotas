import React, {useState,ChangeEvent, FormEvent} from 'react';
import '../../styles/SignUp.css';
import { Link, useHistory } from 'react-router-dom';
import { FiArrowLeft } from "react-icons/fi";
import { ToastProvider, useToasts } from 'react-toast-notifications'
import * as Yup from 'yup';


import Logo from '../../assets/chart3.jpg';
import Chart from '../../assets/chart2.png';
import api from '../../services/api';


interface InstitutionResponse {
    id: Number
}

const SignUpInstitution = () => {
    const history = useHistory();
    const { addToast } = useToasts();

    function addMessageToast() {
        addToast('Cadastrado com sucesso.', {
            appearance: 'success',
            autoDismiss: true,
        })
    }

    const [formData, setFormData] = useState({
        userName:'',
        password: '',
    });

    function handleInputChange(event:ChangeEvent<HTMLInputElement>)
    {
        const { name, value } = event.target;

        setFormData({...formData, [name]: value});
    }

    async function validateData(data: object){
        const schema = Yup.object().shape({
            userName: Yup.string().required('Nome obrigatório'),
            inst: Yup.number().min(1),
            password: Yup.string().min(4, 'No mínimo 4 caracteres'),
        });

        await schema.validate(data, {
            abortEarly: false,
        });
    }

    async function handleSubmit(event: FormEvent){
        try {
            event.preventDefault();

            const{userName, password} = formData;
            const tipo = 'I';
    
            const institution = {
                name: userName
            }
            const institutionResponse = await api.post<InstitutionResponse>('teaching-institution', institution);
    
           
            const data = {
                userName,
                password,
                teachingInstitution:{
                    id:institutionResponse.data.id,
                },
                type:tipo
            }

            await validateData(data);
    
            await api.post('user',data);
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
                    <input name="userName" id="userName" onChange={handleInputChange} className="input" placeholder="Digite o nome da instituição"></input>
                    <input type="password" name="password" id="password" onChange={handleInputChange} className="input" placeholder="Digite sua senha"></input>
                    <button type="submit">Cadastrar</button>
                </form>
                <div className="back-home">
                    <FiArrowLeft />
                    <Link to="">Voltar para home</Link>
                </div>
            </div>
        </div>
    )
}

export default SignUpInstitution;