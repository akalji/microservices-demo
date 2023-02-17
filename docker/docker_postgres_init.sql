create database msd_songs;
create user msd_songs with encrypted password 'msd_songs';
grant all privileges on database msd_songs to msd_songs;

create database msd_resources;
create user msd_resources with encrypted password 'msd_resources';
grant all privileges on database msd_resources to msd_resources;