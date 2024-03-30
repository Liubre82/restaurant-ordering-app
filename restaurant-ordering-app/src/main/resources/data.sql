INSERT INTO menu_categories VALUES (1, 'Appetizers'), (2, 'Steaks'), (3, 'Desserts'), (4, 'Beverages'), (5, 'Noodles'),
(6, 'Soups');


INSERT INTO food_sizes VALUES (1, 'Small'), (2, 'Medium'), (3, 'Large'), (4, '4oz'), (5, '8oz'), (6, '12oz'), (7, '16oz'), (8, 'regular');


INSERT INTO food_items VALUES
('8e9831e5-2a26-4ff6-ac38-8562b0d4a904', 6, 'Lobster Bisque', 'A creamy blend of delectable morsels of lobster meat, celery, onions, and sherry are simmered with our own delicious mixture of spices in a savory base.'),
('ea8d63fd-3eca-4cef-af1d-d2887c58a485', 6, 'Tom Yum Soup', 'Shrimp,mushroom,tomato,cilantro in lemon grass hot&sour soup'),
('828258d7-6c48-4945-8b55-6e5547e37939', 6, 'Clam Chowder', 'New England Clam Chowder is rich with tender chopped sea clams, butter, potatoes, onions, celery, and a blend of spices simmered in a classic, creamy chowder.'),
('54c766ef-88ad-4854-95c7-dcf3eb2117bb', 6, 'Miso Soup', 'Japanese miso soup with dashi stock and classic ingredients like tofu and wakame seaweed.'),

('da5e7624-10f6-4e2c-b2e8-0040288aa361', 1, 'Fried Calamari', ' soaked in buttermilk, then coated in seasoned flour and deep fried to golden brown perfection, with fresh marinara sauce'),
('7942d9a9-33dc-4c2b-b27e-cfdf0c29762b', 1, 'Pork Gyoza', '6 pcs pan-fried pork dumpling with ultra thin rice flour wrapper.'),
('50e5abb1-a6e0-4940-8911-94819a63807c', 1, 'Takoyaki', '6 pcs Octopus dumplings, bonito flakes, japanese mayo, and eel sauce'),
('7f442664-ba71-46d3-a833-8ff13e240ece', 1, 'Bruschetta', 'A symphony of flavors awaits as toasted artisanal bread is generously topped with ripe tomatoes, garlic, basil, and extra virgin olive oil.'),

('7cf27fea-7fca-4890-8595-4978fe911247', 2, 'New York Strip Steak', 'NY Strip Steak wood fire girlled with a touch of our special seasoning.'),
('594343dd-8e0d-4590-be8d-9d1b22cbe633', 2,'Miazaki Waygu', '100g A5 wagyu imported from Japan, seared to absolute perfection. Paired with ponzu sauce.'),
('1c60711f-42a0-4b90-bf87-e041ddf070f4', 2, 'Ribeye Steak', 'Ribeye Steak drizzled with wine reduction and pea puree.'),

('e25b7092-be43-411f-9541-5c52b87181b0', 3, 'Conconut Milk Drink', NULL),
('4f87f882-f119-419d-8d01-0ed6c782860f', 3, 'Sprite', Null),
('685a6a6b-76ff-4ba1-87f3-f5f1ad489708', 3, 'Dr.Pepper', NULL),
('bec085f2-3c7d-40fc-9585-5389b6ddcdd7', 3, 'Taro Bubble Milk Tea', NULL),
('571e1731-627f-45e4-b1a2-75846f049096', 3, 'Avacado Smoothie', NULL),

('022d2c17-c763-4667-ac37-fd2f279a4cfb', 4, 'KABOCHA PIE', 'vegan & gluten free pumpkin custard, rolled oats and flaxseed crumble with coconut sorbet'),
('d5397d1a-60d7-44d2-8935-0491fb461a86', 4, 'EYE OF THE DRAGON', 'sticky longan pudding cake, soaked in palm sugar caramel sauce served with condensed milk ice cream'),
('a744e787-bbf3-4a40-9ed9-fc8bc59f23b6', 4, 'AUTUMN YUZU TRIFLE', 'layered lemon chiffon cake, soaked in verbena tea, poached red wine pear and homemade mascarpone cream'),
('6a3b30d7-c980-4b13-a673-48665ca451f6', 4, 'GOLDEN TOAST', 'warm crispy honey buttered toast, strawberries, served with condensed milk ice cream'),
('0fe4eb77-def9-46ad-be2e-5f8c87293f3a', 4, 'CARAMEL MISO COOKIE', NULL),

('b1f29a9c-d492-4198-ad30-8b828beb7625', 5, 'Tonkotzu Ramen', 'Creamy pork bone broth base. Served with corn, bamboo shoots, Seasoned boiled egg, scallions, wakame, fish cake, sesame seeds, and nori.'),
('b207bcfd-8293-4dca-9626-b56421e8e54a', 5, 'KANJI MYSTERY', 'A spice-lovers paradise. Served with fried pork skin, kimchi, woodear mushroom, bean sprouts, scallions, clam meat and minced pork.'),
('7edf61fb-1282-4bc7-b7c1-0908750e1961', 5, 'Miso Udon Noodles', 'Miso paste mixed into our turkey and chicken mix soup base.Served with bamboo shoots, bean sprouts, corn, scallions, fish cake, sesame seeds, and nori.'),
('689fb8a4-8b24-43fa-8483-7338768b8dd5', 5, 'Classic Style Phó Noodle Soup', 'served with beef broth, onions, scallions. With lemon, basil and bean sprouts on the side.'),
('dcbcf0ac-069d-4aa5-be94-ffc8be16b6cc', 5, 'Spicy Beef Stew Phó Noodle Soup', 'Served with beef stew broth, with beef stew, carrots, onions, scallions.');


INSERT INTO food_item_variations VALUES
(1, '8e9831e5-2a26-4ff6-ac38-8562b0d4a904', 1, 4.99),
(2, '8e9831e5-2a26-4ff6-ac38-8562b0d4a904', 3, 6.40),

(3, 'ea8d63fd-3eca-4cef-af1d-d2887c58a485', 8, 5.99),

(4, '828258d7-6c48-4945-8b55-6e5547e37939', 1, 4.40),
(5, '828258d7-6c48-4945-8b55-6e5547e37939', 3, 5.99),

(6, '54c766ef-88ad-4854-95c7-dcf3eb2117bb', 1, 2.99),
(7, '54c766ef-88ad-4854-95c7-dcf3eb2117bb', 3, 4.25),

(8, 'da5e7624-10f6-4e2c-b2e8-0040288aa361', 8, 14.99),
(9, '7942d9a9-33dc-4c2b-b27e-cfdf0c29762b', 8, 6.00),
(10, '50e5abb1-a6e0-4940-8911-94819a63807c', 8, 8.95),
(11, '7f442664-ba71-46d3-a833-8ff13e240ece', 8, 16.99),

(12, '7cf27fea-7fca-4890-8595-4978fe911247', 5, 19.99),
(13, '7cf27fea-7fca-4890-8595-4978fe911247', 6, 30.45),

(14, '594343dd-8e0d-4590-be8d-9d1b22cbe633', 8, 329.99),

(15, '1c60711f-42a0-4b90-bf87-e041ddf070f4', 6, 25.99),
(16, '1c60711f-42a0-4b90-bf87-e041ddf070f4', 7, 34.99),

(17, 'e25b7092-be43-411f-9541-5c52b87181b0', 1, 4.99),
(18, 'e25b7092-be43-411f-9541-5c52b87181b0', 2, 5.99),
(19, 'e25b7092-be43-411f-9541-5c52b87181b0', 3, 7.25),

(20, '4f87f882-f119-419d-8d01-0ed6c782860f', 8, 1.99),
(21, '685a6a6b-76ff-4ba1-87f3-f5f1ad489708', 8, 1.99),

(22, 'bec085f2-3c7d-40fc-9585-5389b6ddcdd7', 1, 5.65),
(23, 'bec085f2-3c7d-40fc-9585-5389b6ddcdd7', 2, 6.90),
(24, 'bec085f2-3c7d-40fc-9585-5389b6ddcdd7', 3, 7.99),

(25, '571e1731-627f-45e4-b1a2-75846f049096', 1, 5.99),
(26, '571e1731-627f-45e4-b1a2-75846f049096', 2, 6.99),
(27, '571e1731-627f-45e4-b1a2-75846f049096', 3, 8.50),

(28, '022d2c17-c763-4667-ac37-fd2f279a4cfb', 8, 14.45),
(29, 'd5397d1a-60d7-44d2-8935-0491fb461a86', 8, 14.45),
(30, 'a744e787-bbf3-4a40-9ed9-fc8bc59f23b6', 8, 15.99),
(31, '6a3b30d7-c980-4b13-a673-48665ca451f6', 8, 13.25),
(32, '0fe4eb77-def9-46ad-be2e-5f8c87293f3a', 8, 4.45),
(33, 'b1f29a9c-d492-4198-ad30-8b828beb7625', 8, 18.99),
(34, 'b207bcfd-8293-4dca-9626-b56421e8e54a', 8, 15.95),
(35, '7edf61fb-1282-4bc7-b7c1-0908750e1961', 8, 16.45),
(36, '689fb8a4-8b24-43fa-8483-7338768b8dd5', 8, 16.50),
(37, 'dcbcf0ac-069d-4aa5-be94-ffc8be16b6cc', 8, 20.99);


INSERT INTO food_images VALUES
(1, '8e9831e5-2a26-4ff6-ac38-8562b0d4a904', 'Lobster Bisque IMAGE'),
(2, 'ea8d63fd-3eca-4cef-af1d-d2887c58a485', 'Tom Yum Soup IMAGE'),
(3, '828258d7-6c48-4945-8b55-6e5547e37939', 'Clam Chowder IMAGE'),
(4, '54c766ef-88ad-4854-95c7-dcf3eb2117bb', 'Miso Soup IMAGE'),

(5, 'da5e7624-10f6-4e2c-b2e8-0040288aa361', 'Fried Calamari IMAGE'),
(6, '7942d9a9-33dc-4c2b-b27e-cfdf0c29762b', 'Pork Gyoza IMAGE'),
(7, '50e5abb1-a6e0-4940-8911-94819a63807c', 'Takoyaki IMAGE'),
(8, '7f442664-ba71-46d3-a833-8ff13e240ece', 'Bruschetta IMAGE'),

(9, '7cf27fea-7fca-4890-8595-4978fe911247', 'New York Strip Steak IMAGE'),
(10, '594343dd-8e0d-4590-be8d-9d1b22cbe633', 'Miazaki Waygu IMAGE'),
(11, '1c60711f-42a0-4b90-bf87-e041ddf070f4', 'Ribeye Steak IMAGE'),

(12, 'e25b7092-be43-411f-9541-5c52b87181b0', 'Conconut Milk Drink IMAGE'),
(13, '4f87f882-f119-419d-8d01-0ed6c782860f', 'Sprite IMAGE'),
(14, '685a6a6b-76ff-4ba1-87f3-f5f1ad489708', 'Dr.Pepper IMAGE'),
(15, 'bec085f2-3c7d-40fc-9585-5389b6ddcdd7', 'Taro Bubble Milk Tea IMAGE'),
(16, '571e1731-627f-45e4-b1a2-75846f049096', 'Avacado Smoothie IMAGE'),

(17, '022d2c17-c763-4667-ac37-fd2f279a4cfb', 'KABOCHA PIE IMAGE'),
(18, 'd5397d1a-60d7-44d2-8935-0491fb461a86', 'EYE OF THE DRAGON IMAGE'),
(19, 'a744e787-bbf3-4a40-9ed9-fc8bc59f23b6', 'AUTUMN YUZU TRIFLE IMAGE'),
(20, '6a3b30d7-c980-4b13-a673-48665ca451f6', 'GOLDEN TOAST IMAGE'),
(21, '0fe4eb77-def9-46ad-be2e-5f8c87293f3a', 'CARAMEL MISO COOKIE IMAGE'),

(22, 'b1f29a9c-d492-4198-ad30-8b828beb7625', 'Tonkotzu Ramen IMAGE'),
(23, 'b207bcfd-8293-4dca-9626-b56421e8e54a', 'KANJI MYSTERY IMAGE'),
(24, '7edf61fb-1282-4bc7-b7c1-0908750e1961', 'Miso Udon Noodles IMAGE'),
(25, '689fb8a4-8b24-43fa-8483-7338768b8dd5', 'Classic Style Phó Noodle Soup IMAGE'),
(26, 'dcbcf0ac-069d-4aa5-be94-ffc8be16b6cc', 'Spicy Beef Stew Phó Noodle Soup IMAGE'),
(27, '8e9831e5-2a26-4ff6-ac38-8562b0d4a904', 'Lobster Bisque IMAGE'),
(28, 'ea8d63fd-3eca-4cef-af1d-d2887c58a485', 'Tom Yum Soup IMAGE'),
(29, '828258d7-6c48-4945-8b55-6e5547e37939', 'Clam Chowder IMAGE'),
(30, '54c766ef-88ad-4854-95c7-dcf3eb2117bb', 'Miso Soup IMAGE'),

(31, 'da5e7624-10f6-4e2c-b2e8-0040288aa361', 'Fried Calamari IMAGE'),
(32, '7942d9a9-33dc-4c2b-b27e-cfdf0c29762b', 'Pork Gyoza IMAGE'),
(33, '50e5abb1-a6e0-4940-8911-94819a63807c', 'Takoyaki IMAGE'),
(34, '7f442664-ba71-46d3-a833-8ff13e240ece', 'Bruschetta IMAGE'),

(35, '7cf27fea-7fca-4890-8595-4978fe911247', 'New York Strip Steak IMAGE'),
(36, '594343dd-8e0d-4590-be8d-9d1b22cbe633', 'Miazaki Waygu IMAGE'),
(37, '1c60711f-42a0-4b90-bf87-e041ddf070f4', 'Ribeye Steak IMAGE'),

(38, 'e25b7092-be43-411f-9541-5c52b87181b0', 'Conconut Milk Drink IMAGE'),
(39, '4f87f882-f119-419d-8d01-0ed6c782860f', 'Sprite IMAGE'),
(40, '685a6a6b-76ff-4ba1-87f3-f5f1ad489708', 'Dr.Pepper IMAGE'),
(41, 'bec085f2-3c7d-40fc-9585-5389b6ddcdd7', 'Taro Bubble Milk Tea IMAGE'),
(42, '571e1731-627f-45e4-b1a2-75846f049096', 'Avacado Smoothie IMAGE'),

(43, '022d2c17-c763-4667-ac37-fd2f279a4cfb', 'KABOCHA PIE IMAGE'),
(44, 'd5397d1a-60d7-44d2-8935-0491fb461a86', 'EYE OF THE DRAGON IMAGE'),
(45, 'a744e787-bbf3-4a40-9ed9-fc8bc59f23b6', 'AUTUMN YUZU TRIFLE IMAGE'),
(46, '6a3b30d7-c980-4b13-a673-48665ca451f6', 'GOLDEN TOAST IMAGE'),
(47, '0fe4eb77-def9-46ad-be2e-5f8c87293f3a', 'CARAMEL MISO COOKIE IMAGE'),

(48, 'b1f29a9c-d492-4198-ad30-8b828beb7625', 'Tonkotzu Ramen IMAGE'),
(49, 'b207bcfd-8293-4dca-9626-b56421e8e54a', 'KANJI MYSTERY IMAGE'),
(50, '7edf61fb-1282-4bc7-b7c1-0908750e1961', 'Miso Udon Noodles IMAGE'),
(51, '689fb8a4-8b24-43fa-8483-7338768b8dd5', 'Classic Style Phó Noodle Soup IMAGE'),
(52, 'dcbcf0ac-069d-4aa5-be94-ffc8be16b6cc', 'Spicy Beef Stew Phó Noodle Soup IMAGE');





-- Users Related Schema.
INSERT INTO user_roles VALUES
('3d1701f1-4d66-4064-be1e-2d0b9bb4161a', 'customer'),
('c8d352e8-6cbd-4652-9a09-0fcf45e0a817', 'employee');



