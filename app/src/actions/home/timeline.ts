import {TimeLine} from  '../../constants/home/timeline';

export interface ISharedItem {
  id : string,
  title: string,
  content: string,
} 

export interface IAction extends ISharedItem{
  type: TimeLine
}

export function addSharedItem(sharedItem:ISharedItem){
  return{
    id: sharedItem.id,
    title: sharedItem.title,
    content: sharedItem.content,
    type: TimeLine.ADD_SHAREDITEM
  } 
}
