import React, { useEffect, useState, ChangeEvent } from 'react';
import User from '../../assets/user.png';
import api from '../../services/api';
import '../../styles/EditInstitution.css'
import { Link, useHistory } from 'react-router-dom';
import Logoff from '../../helpers/Logoff';
import { useToasts } from 'react-toast-notifications'


interface institution {
    name: String,
    id: number,
}

interface User {
    id: number,
    userName: string,
    password: String,
    type: String,
    teachingInstitution: {
        name: String,
        id: number,
    }
}
const EditInstitution = () => {
    const { addToast } = useToasts();
    const [institutions, setInstitutions] = useState<institution[]>([]);
    const [selectedInstitution, setSelectedInstitution] = useState("0");
    const [user, setUser] = useState<User>();
    const history = useHistory();
    const [username, setUsername] = useState("");

    function handleInputChange(event: ChangeEvent<HTMLInputElement>) {
        const { name, value } = event.target;

        setUsername(value);
    }

    useEffect(() => {
        api.get('teaching-institution').then(response => {
            setInstitutions(response.data);
        });

        const user = localStorage.getItem('@FTT:user');

        if (user) {
            const parsedUser = JSON.parse(user);
            setUser(parsedUser);
            if (parsedUser.type === "A") {
                history.push('/home')
            }
        }
        else {
            history.push('/');
            return;
        }
    }, []);

    function addMessageToast() {
        addToast('Alterado com sucesso.', {
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



    async function handleEdit() {

        if(username.length === 0){
            addMessageToastError("Nome da instituição vazio.");
            return;
        }

        const data = {
            id: user?.id,
            userName: username,
            password: "",
            teachingInstitution: {
                id: user?.teachingInstitution.id
            },
            type: 'I'
        }
        await api.put('user', data);
        addMessageToast();
        localStorage.setItem('@FTT:user',JSON.stringify(data));
        history.push('/home-institution')
    }

    async function handleDelete() {
        await api.delete(`/user/${user?.id}`);
        Logoff();
        history.push('/');
    }

    return (
        <div id="edit-student-container">
            <img alt="user" src={User} />
            <label htmlFor="username">Nome instituição</label>
            <div className="custom-container">
                <input name="username" type="text" placeholder={user?.userName} onChange={handleInputChange}  />
                <button onClick={handleEdit}>Atualizar nome</button>

            </div>
            <button className="button-delete" onClick={handleDelete}>Apagar conta</button>
            <Link to="/home">Voltar para home</Link>
        </div>
    )
}

export default EditInstitution;