import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import SignIn from './pages/SignIn/SignIn';
import SignUp from './pages/SignUp/SignUp';
import SignUpInstitution from './pages/SignUp/SignUpInstitution';
import Home from './pages/Home/Home';
import HomeInstitution from './pages/Home/HomeInstitution';
import Subjects from './pages/Subjects/Subjects';
import EditStudent from './pages/Edit/EditStudent';

function Routes() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={SignIn} />
                <Route path="/signup" component={SignUp} />
                <Route path="/signupInstitution" component={SignUpInstitution} />
                <Route path="/home" component={Home} />
                <Route path="/home-institution" component={HomeInstitution} />
                <Route path="/subjects" component={Subjects} />
                <Route path="/edit-student" component={EditStudent} />
            </Switch>
        </BrowserRouter>
    )
}

export default Routes;