<template>
  <v-app>
    <v-app-bar app dark color = "green darken-1">
      <v-btn to="/customers" text>
        Customers information Details
      </v-btn>
    </v-app-bar>

    <v-main>
      <router-view />
    </v-main>
  </v-app>
</template>

<script>

export default {
  name: 'App',

  components: {
  },

  data: () => ({
    //
  }),
};
</script>
