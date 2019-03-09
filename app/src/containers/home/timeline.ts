import {TimeLine} from '../../components/home/TimeLine';
import { connect } from 'react-redux';
import {addSharedItem, ISharedItem}  from '../../actions/home/timeline'

export const mapDispatchToProps = (dispatch) => {
  return {
    addSharedItem: (sharedItem: ISharedItem) => {
      dispatch(addSharedItem(sharedItem));
    }
  };
};

export const mapStateToProps = (sharedItems: ISharedItem[]) => {
  return {
    sharedItems
  };
}

export default connect(mapStateToProps, mapDispatchToProps)(TimeLine);