import React, { useEffect, useState, ChangeEvent } from 'react';
import User from '../../assets/user.png';
import api from '../../services/api';
import '../../styles/EditStudent.css'
import { Link, useHistory } from 'react-router-dom';
import Logoff from '../../helpers/Logoff';
import { useToasts } from 'react-toast-notifications'


interface institution {
    name: String,
    id: number,
}

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
const EditStudent = () => {

    const [institutions, setInstitutions] = useState<institution[]>([]);
    const [selectedInstitution, setSelectedInstitution] = useState("0");
    const [user, setUser] = useState<User>();
    const history = useHistory();
    const { addToast } = useToasts();

    useEffect(() => {
        api.get('teaching-institution').then(response => {
            setInstitutions(response.data);
        });

        const user = localStorage.getItem('@FTT:user');

        if (user) {
            const parsedUser = JSON.parse(user);
            setUser(parsedUser);
            if (parsedUser.type === "I") {
                history.push('/home-institution')
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


    function handleSelectInstitution(event: ChangeEvent<HTMLSelectElement>) {
        const inst = event.target.value;
        setSelectedInstitution(inst);
    }

    async function handleEdit() {
        const inst = Number(selectedInstitution);

        if (inst <= 0){
            addMessageToastError("Selecione uma instituição.");
            return;
        }

            const data = {
                id: user?.id,
                userName: user?.userName,
                password: "",
                teachingInstitution: {
                    id: inst
                },
                type: 'A'
            }

        await api.put('user', data);
        addMessageToast();
        history.push('/home');
    }

    async function handleDelete() {
        await api.delete(`/user/${user?.id}`);
        Logoff();
        history.push('/');
    }

    return (
        <div id="edit-student-container">
            <img alt="user" src={User} />
            <div className="custom-container">
                <select name="institution" id="institution" value={selectedInstitution} onChange={handleSelectInstitution} className="select">
                    <option value="0">Escolha sua faculdade</option>
                    {institutions.map(inst => (
                        <option key={inst.id} value={inst.id} >{inst.name}</option>
                    ))}
                </select>
                <button onClick={handleEdit}>Atualizar conta</button>

            </div>
            <button className="button-delete" onClick={handleDelete}>Apagar conta</button>
            <Link to="/home">Voltar para home</Link>
        </div>
    )
}

export default EditStudent;