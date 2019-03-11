import {TimeLine}  from  '../constants/home/timeline';
import {ISharedItem, IAction}  from '../actions/home/timeline';


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