import {TimeLine} from '../../components/home/TimeLine';
import { connect } from 'react-redux';
import {addSharedItem, ISharedItem, deleteSharedItem, updateSharedItem }  from '../../actions/home/timeline'

export const mapDispatchToProps = (dispatch) => {
  return {
    addSharedItem: (sharedItem: ISharedItem) => {
      dispatch(addSharedItem(sharedItem));
    },
    fetchItems: () => {
       fetch('http://127.0.0.1:8080/timeline/').then(x => x.json()).then((response:ISharedItem[]) => {
                response.forEach(data => {
                  dispatch(addSharedItem(data));
                })
        });
    },
    updateSharedItem: (sharedItem : ISharedItem) => {
      fetch('http://localhost:8080/timeline/' + sharedItem.id,{
        method: 'PUT',
        body: JSON.stringify(sharedItem)
      }).then((response) => {
        if(response.status == 200) {
          dispatch(updateSharedItem(sharedItem));
        }
      })
    },
    deleteSharedItem: (id :string) => {
      fetch('http://localhost:8080/timeline/' + id,{
        method: 'DELETE'
      }).then((response) => {
        if(response.status == 200) {
          dispatch(deleteSharedItem(id));
        }
      })
    },
  };
};

export const mapStateToProps = (sharedItems: ISharedItem[]) => {
  return {
    sharedItems
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(TimeLine);