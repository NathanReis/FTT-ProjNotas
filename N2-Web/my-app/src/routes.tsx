import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import SignIn from './pages/SignIn/SignIn';
import SignUp from './pages/SignUp/SignUp';
import Home from './pages/Home/Home';

function Routes() {
    return (
        <BrowserRouter>
            <Switch>
                <Route path="/" exact component={SignIn} />
                <Route path="/signup" component={SignUp} />
                <Route path="/home" component={Home} />
            </Switch>
        </BrowserRouter>
    )
}

export default Routes;