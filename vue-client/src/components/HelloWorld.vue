<template>
  <div class="hello">
    <ul>
      <li v-for="user in userGraphQL.usersPaginated">{{user.name}}</li>
    </ul>
    <p>Total elements : {{userGraphQL.userCount}}</p>
  </div>
</template>

<script>
  import gql from 'graphql-tag';

  export default {
    name: 'HelloWorld',
    data() {
      return {
        userGraphQL: { usersPaginated: null }
      }
    },
    apollo: {
      userGraphQL: {
        query: gql`
            query UsersPaginated($page:Int, $count:Int, $sortedBy:String) {
              userCount
              usersPaginated(page:$page, count:$count, sortedBy:$sortedBy){
                  name
              }
            }`,
        variables: {
          page: 1,
          count: 5,
          sortedBy: 'name'
        },
        update: result => result
      }
    }
  }
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
