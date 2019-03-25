import * as React from 'react';
import { Button, Form, Grid, Header, Image, Message, Segment } from 'semantic-ui-react'
import { ILoginForm } from 'src/actions/auth';

interface IProps {
  states: any,
  login(loginForm : ILoginForm):any,
  }
  
  interface IContext {
  loginForm?: ILoginForm;
  };
  

export class LoginForm extends React.Component<IProps,IContext> {

    constructor(props) {
        super(props);

        this.state = {
          loginForm : {
              username:  this.props.states.loginForm.username ,
              password:  this.props.states.loginForm.password
          }
        }
    }
 
    public login = () =>  {
      this.props.login(this.props.states.loginForm);
    }

    public onChange = (e) => {      
      
       this.setState({loginForm: { 
            username: e.target.id === "username" ? e.target.value : "",
            password:  e.target.id === "password" ? e.target.value : ""
        }});

        if(e.target.id === "username") {
          this.props.states.loginForm.username =  e.target.value 
        }
        else {
          this.props.states.loginForm.password =  e.target.value 
        }
    }



    public render() {
      return(
        <div className='login-form'>
          <style>{`
            body > div,
            body > div > div,
            body > div > div > div.login-form {
              height: 100%;
            }
          `}
          </style>
          <Grid textAlign='center' style={{ height: '100%' }} verticalAlign='middle'>
          <Grid.Column style={{ maxWidth: 450 }}>
            <Header as='h2' color='teal' textAlign='center'>
              <Image src='/logo.png' /> Log-in to your account
            </Header>
            <Form size='large'>
              <Segment stacked>
                <Form.Input fluid icon='user' iconPosition='left' id="username" value={this.props.states.loginForm.username } onChange={(e) => {this.onChange(e)}}  placeholder='E-mail address' />
                <Form.Input
                  fluid
                  icon='lock'
                  iconPosition='left'
                  placeholder='Password'
                  id="password"
                  onChange={(e) => {this.onChange(e)}} 
                  value={this.props.states.loginForm.password} 
                  type='password'
                />
    
                <Button color='teal' fluid size='large' onClick={this.login}>
                  Login
                </Button>
              </Segment>
            </Form>
            <Message>
              New to us? <a href='#'>Sign Up</a>
            </Message>
          </Grid.Column>
        </Grid>
      </div>
      ) 
    } 
}
