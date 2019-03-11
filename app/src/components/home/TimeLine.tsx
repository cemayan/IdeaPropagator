import * as React from 'react';
import {ISharedItem} from  '../../actions/home/timeline'
import { SearchBox } from './SearchBox';
import { Divider, Feed, Icon } from 'semantic-ui-react';

interface IProps {
sharedItems? : ISharedItem[],
addSharedItem(sharedItem: ISharedItem): void,
fetchItems():any,
deleteSharedItem(id :string): void
}

interface IContext {
sharedItems?: ISharedItem[];
};


export class TimeLine extends React.Component<IProps,IContext> {

  public async componentWillMount() {
      this.props.fetchItems()
  }


  render() {
    return (
      <div>

         <SearchBox/>
         <Divider/>

         <Feed>
         {this.props.sharedItems.map(sharedItem => (
          <Feed.Event>
            <Feed.Label image='https://react.semantic-ui.com/images/avatar/small/joe.jpg' />
            <Feed.Content>
              <Feed.Summary>
                <a>Cem Ayan</a> posted on his page
                <Feed.Date>3 days ago</Feed.Date>
              </Feed.Summary>
              <Feed.Extra text>
                {sharedItem.content}
              </Feed.Extra>
              <Feed.Meta>
                <Feed.Like>
                  <Icon name='like' />
                  5 Likes
                </Feed.Like>
              </Feed.Meta>
            </Feed.Content>
          </Feed.Event>
       ))
      }  
      </Feed>
               {/* <a onClick={(e)=>this.props.deleteSharedItem(sharedItem.id)}>a</a> */}
      </div>
    );
  }
}
