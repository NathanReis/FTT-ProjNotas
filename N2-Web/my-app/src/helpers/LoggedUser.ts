interface User{
    userName: String,
    password: String,
    type: String,
    teachingInstitution: {
        name: String;
    }
}

const GetLoggedUser = ():User=> {
    const user = localStorage.getItem('@FTT:user');

    if (user){
        const parsedUser = JSON.parse(user);
        return parsedUser;
    }
    return {} as User;
}

const LoggedUser = GetLoggedUser();

export default LoggedUser;