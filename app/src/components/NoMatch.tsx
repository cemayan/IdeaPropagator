import * as React from 'react';
import {  Container,Image,Menu } from 'semantic-ui-react'
import './App.css';
import {BrowserRouter as Router,Route,Link, Redirect, Switch} from 'react-router-dom';
import TimeLine  from '../containers/home/TimeLine';
import  LoginForm from '../containers/auth/LoginForm';
import {history} from '../helpers/history';


class NoMatch extends React.Component<any,any> {

  public render() {
    return (

        <div>
            Wrong Page.
        </div>

    );
  }
}


export default NoMatch;
