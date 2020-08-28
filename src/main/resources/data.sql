INSERT INTO item(id, name, short_Desc, description, price, img_Url) VALUES
  (1, 'Margherita', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus porttitor rhoncus egestas.', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis interdum in mi vitae pretium. Nulla eu malesuada tortor. Maecenas at libero quis eros aliquam fermentum. Fusce hendrerit libero et metus porttitor, eu efficitur mauris blandit. Suspendisse felis sapien, commodo viverra nunc at, congue sollicitudin augue. Suspendisse potenti. Vestibulum justo lorem, fermentum id ipsum vel, facilisis ultrices diam. Mauris lobortis risus in dolor rhoncus, nec imperdiet ipsum pharetra. Suspendisse tortor magna, elementum quis tincidunt non, egestas nec felis.', 23.0, '../img/margherita.jpg'),
  (2, 'Capriciosa', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus porttitor rhoncus egestas.', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis interdum in mi vitae pretium. Nulla eu malesuada tortor. Maecenas at libero quis eros aliquam fermentum. Fusce hendrerit libero et metus porttitor, eu efficitur mauris blandit. Suspendisse felis sapien, commodo viverra nunc at, congue sollicitudin augue. Suspendisse potenti. Vestibulum justo lorem, fermentum id ipsum vel, facilisis ultrices diam. Mauris lobortis risus in dolor rhoncus, nec imperdiet ipsum pharetra. Suspendisse tortor magna, elementum quis tincidunt non, egestas nec felis.', 26.0, '../img/capriciosa.jpg'),
  (3, 'Salami', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus porttitor rhoncus egestas.', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis interdum in mi vitae pretium. Nulla eu malesuada tortor. Maecenas at libero quis eros aliquam fermentum. Fusce hendrerit libero et metus porttitor, eu efficitur mauris blandit. Suspendisse felis sapien, commodo viverra nunc at, congue sollicitudin augue. Suspendisse potenti. Vestibulum justo lorem, fermentum id ipsum vel, facilisis ultrices diam. Mauris lobortis risus in dolor rhoncus, nec imperdiet ipsum pharetra. Suspendisse tortor magna, elementum quis tincidunt non, egestas nec felis.', 28.0, '../img/salami.jpg'),
  (4, 'Wegetariańska', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Phasellus porttitor rhoncus egestas.', 'Lorem ipsum dolor sit amet, consectetur adipiscing elit. Duis interdum in mi vitae pretium. Nulla eu malesuada tortor. Maecenas at libero quis eros aliquam fermentum. Fusce hendrerit libero et metus porttitor, eu efficitur mauris blandit. Suspendisse felis sapien, commodo viverra nunc at, congue sollicitudin augue. Suspendisse potenti. Vestibulum justo lorem, fermentum id ipsum vel, facilisis ultrices diam. Mauris lobortis risus in dolor rhoncus, nec imperdiet ipsum pharetra. Suspendisse tortor magna, elementum quis tincidunt non, egestas nec felis.', 27.0, '../img/wege.jpg');

INSERT INTO client_order(id, address, telephone, status) VALUES
(1, 'Zakrzewskiego 23/5, 50-225 Wrocław', '888777666', 'NEW'),
(2, 'Kościuszki 13, 52-316 Wrocław', '767454989', 'NEW'),
(3, 'Krakowska 88/16, 51-515 Wrocław', '666234123', 'IN_PROGRESS'),
(4, 'Centralna 8/12, 55-100 Wrocław', '598787999', 'IN_PROGRESS'),
(5, 'Dworcowa 33, 51-200 Wrocław', '600700900', 'FINISHED'),
(6, 'Krucza 66/4, 53-300 Wrocław', '696787898', 'FINISHED');

INSERT INTO order_item (order_id, item_id) VALUES
(1, 1),
(1, 2),
(1, 3),
(2, 3),
(2, 4),
(3, 1),
(3, 1),
(3, 1),
(4, 1),
(4, 2),
(5, 3),
(5, 3),
(5, 4),
(6, 4),
(6, 4);

INSERT INTO admin(id, login, password, role, created_on) VALUES
(1, 'user1', 'pass', 'ADMIN', CURRENT_TIMESTAMP),
(2, 'user2', 'pass', 'SUPER_ADMIN', CURRENT_TIMESTAMP),
(3, 'user3', 'pass', 'ADMIN', CURRENT_TIMESTAMP),
(4, 'user4', 'pass', 'ADMIN', CURRENT_TIMESTAMP),
(5, 'user5', 'pass', 'SUPER_ADMIN', CURRENT_TIMESTAMP),
(6, 'admin', 'pass', 'SUPER_ADMIN', CURRENT_TIMESTAMP);
