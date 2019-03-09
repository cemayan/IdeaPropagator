import {TimeLine}  from  '../constants/home/timeline';
import {ISharedItem, IAction}  from '../actions/home/timeline'

export const sharedItems = (state:ISharedItem[] =[], action :IAction) =>{

  switch(action.type){
    case  TimeLine.ADD_SHAREDITEM:
      return [
        ...state,
        {
          id : action.id ,
          title: action.title, 
          content : action.content,
        } ] 
    default:
      return state;  
  }
} 