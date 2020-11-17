import React from 'react';
import { BrowserRouter, Route, Switch } from 'react-router-dom';

import SignIn from './pages/SignIn/SignIn';
import SignUp from './pages/SignUp/SignUp';
import SignUpInstitution from './pages/SignUp/SignUpInstitution';
import Home from './pages/Home/Home';
import HomeInstitution from './pages/Home/HomeInstitution';
import Subjects from './pages/Subjects/Subjects';
import SubjectsInstitution from './pages/Subjects/SubjectsInstitution';
import SubjectsInstitutionEdit from './pages/Subjects/SubjectsInstitutionEdit';
import EditStudent from './pages/Edit/EditStudent';
import EditInstitution from './pages/Edit/EditInstitution';
import Grades from './pages/Grades/Grades';
import StudentsChart from './pages/Charts/StudentsChart';
import InstitutionsChart from './pages/Charts/InstitutionsChart';

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
                <Route path="/subjects-institution" component={SubjectsInstitution} />
                <Route path="/subjects-institution-edit" component={SubjectsInstitutionEdit} />
                <Route path="/edit-student" component={EditStudent} />
                <Route path="/edit-institution" component={EditInstitution} />
                <Route path="/student-grades" component={Grades} />
                <Route path="/student-chart" component={StudentsChart} />
                <Route path="/institution-chart" component={InstitutionsChart} />
            </Switch>
        </BrowserRouter>
    )
}

export default Routes;