import {Auth}  from '../../constants/auth/index';

export interface ILoginForm {
  username : string,
  password : string,
  token?: string
} 

export interface IRegisterForm {
  username : string,
  password : string,
} 

export interface ILoginAction  extends ILoginForm,IRegisterForm {
  type : string
} 




export function login(loginForm: ILoginForm) {
  return { 
    username: loginForm.username,
    password: loginForm.password,
    token: loginForm.token,
    type: Auth.LOGIN
  }
}

export function register(registerForm: IRegisterForm) {
  return { 
    username: registerForm.username,
    password: registerForm.password,
    type: Auth.REGISTER
  }
}