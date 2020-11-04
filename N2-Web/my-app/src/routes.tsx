import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import SignIn from './pages/SignIn/SignIn';
import SignUp from './pages/SignUp/SignUp';
import SignUpInstitution from './pages/SignUp/SignUpInstitution';
import Home from './pages/Home/Home';
import Subjects from './pages/Subjects/Subjects';

function Routes() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={SignIn} />
                <Route path="/signup" component={SignUp} />
                <Route path="/signupInstitution" component={SignUpInstitution} />
                <Route path="/home" component={Home} />
                <Route path="/subjects" component={Subjects} />
            </Switch>
        </BrowserRouter>
    )
}

export default Routes;