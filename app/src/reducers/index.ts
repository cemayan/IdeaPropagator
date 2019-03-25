import {TimeLine}  from  '../constants/home/timeline';
import {Auth}  from  '../constants/auth/index';
import {ISharedItem, IAction}  from '../actions/home/timeline';
import {ILoginForm, IRegisterForm, ILoginAction}  from '../actions/auth/index';
import { combineReducers } from 'redux'

export const sharedItems = (state:ISharedItem[] =[], action :IAction) =>{

  let new_state = [...state];

  switch(action.type){
    case  TimeLine.ADD_SHAREDITEM:
      return [
        ...state,
        {
          id : action.id ,
          title: action.title, 
          content : action.content,
        } ] 
    case  TimeLine.UPDATE_SHAREDITEM:
        let item = new_state.find(x => x.id == action.id);  
        item.content = action.content;
        item.title = action.title; 
        return   new_state;
     case  TimeLine.DELETE_SHAREDITEM:
         let index = new_state.findIndex(x => x.id == action.id);  
         new_state.splice(index,1);
         return   new_state;       
    default:
      return state;  
  }
} 

export const loginForm = (state: ILoginForm= {username:"", password: "", token:""}, action: ILoginAction) => {
  switch(action.type) {
    case Auth.LOGIN :
 
      return state;
    default:
      return state;  
  }
}

export default combineReducers({
  sharedItems,
  loginForm
})