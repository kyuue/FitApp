-- phpMyAdmin SQL Dump
-- version 5.1.1
-- https://www.phpmyadmin.net/
--
-- Anamakine: 127.0.0.1
-- Üretim Zamanı: 04 Oca 2022, 06:32:09
-- Sunucu sürümü: 10.4.20-MariaDB
-- PHP Sürümü: 8.0.8

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Veritabanı: `fitapp`
--

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `besinler`
--

CREATE TABLE `besinler` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `type` char(1) COLLATE utf8_bin DEFAULT NULL,
  `unit` char(1) COLLATE utf8_bin DEFAULT NULL,
  `cal` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `besinler`
--

INSERT INTO `besinler` (`id`, `name`, `type`, `unit`, `cal`) VALUES
(1, 'Havuç', 'Y', 'U', 150),
(2, 'Salatalik', 'Y', 'U', 150),
(3, 'Sebze', 'Y', 'G', 200),
(4, 'Ayran', 'I', 'L', 200);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `besintype`
--

CREATE TABLE `besintype` (
  `Type` char(1) COLLATE utf8_bin NOT NULL,
  `Seq` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `besintype`
--

INSERT INTO `besintype` (`Type`, `Seq`) VALUES
('I', 2),
('Y', 1);

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `customer`
--

CREATE TABLE `customer` (
  `id` int(11) NOT NULL,
  `identity_number` text COLLATE utf8_bin NOT NULL,
  `name` text COLLATE utf8_bin NOT NULL,
  `surname` text COLLATE utf8_bin NOT NULL,
  `phone_number` text COLLATE utf8_bin NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `customer`
--

INSERT INTO `customer` (`id`, `identity_number`, `name`, `surname`, `phone_number`) VALUES
(1, '45547961129', 'seradr', 'yazici', '05464927980'),
(2, '45547961124', 'serdar', 'yazici', '05464927980');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `egitmenler`
--

CREATE TABLE `egitmenler` (
  `id` int(11) NOT NULL,
  `name` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `surname` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `tel` varchar(11) COLLATE utf8_bin DEFAULT NULL,
  `about` varchar(250) COLLATE utf8_bin DEFAULT NULL,
  `price` int(11) DEFAULT NULL,
  `photo` longblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `egitmenler`
--

INSERT INTO `egitmenler` (`id`, `name`, `surname`, `tel`, `about`, `price`, `photo`) VALUES
(1, 'asdasd', 'dasd', '05344927912', 'asdasdasd', 5000, 0xffd8ffe000104a46494600010101004800480000ffdb00430006040506050406060506070706080a100a0a09090a140e0f0c1017141818171416161a1d251f1a1b231c1616202c20232627292a29191f2d302d283025282928ffdb0043010707070a080a130a0a13281a161a2828282828282828282828282828282828282828282828282828282828282828282828282828282828282828282828282828ffc2001108012800ec03012200021101031101ffc4001b00000105010100000000000000000000000001020304050607ffc400190101010101010100000000000000000000000102030405ffda000c03010002100310000001d15bcef274cd2f325a45c42936eb6a9a5b618987bd85d658cdd3cfd5cb827afbc306a58f180f180f18a3d630948d524181ed4f4778fa31b2c4ad6aa0d6b906b5cb6e0616fe175cc95ec3ede7a0de97a63966cccb98c7a2b15ef232c485247b015ae15013db5258bc7d5ad73444486d98551999722d4c7ccd78fae69dee8fa0d5cef3cf52f2fb9e753d338fb39b5ebaf2f0d7bd1378e4b1fd17c78c88e68918e4501553db5aadf1f546b52aaa595b5ce048eb6bd4ef99775ced441c594a1d2433eb6c30c6b9039740a17a39bf2fedf3ce421eab9bb2b248d441117db9933fc7d294939a54cfdc421d2c7dcef92dbcde40115a0083058f2e15d8ab9d29a50c3649286938e068fa372cbe7b97d0f3e91807b9ab71fc9d36086c74914fa167a46bd1daca0140351504120b00c5515ad9184756090ad7d9488bcc7db38f5f3382ed54811ec3dda279e4e916f6143db3d32d4b3d72e11051ad1e32917d010128adf1ac1f913d629e958b4152d8235e8bc679dfb973c79145af969ee0da6ef2749d999bbb94fa38a7ef91152cad1d7b4b4f43236a91d8f7529e3a68b56f2693f37b0ad0e66a74b1c5cf275445016f2b53942f7219788ad81d1ccfb8ab17c9d0a56ed7494ba144ed9744ec8d169d982db16b3a6a84a33c567ef7379588e4933a5ad56c696b36dd3b3abcf92a56b71fa1c7c8ee716091ad113dc5521f27593772aefa72f8613ac7d1b54973b4702c62dc4a36b466ee5ee6337b3350b8e5a4e8f2a6f07434239ae4d36d2ca96f02f6d2e354d338aa7b18f9cb1013da61755f2f6d7af55dd63ae63ecf69670f731f533ebdb672dcf62c6a4962ada859baec77dce8b2adb56b256cb2450e759cde6bb9bd216431d96e98920027b3b9cef1fa1947492ca3258abda5a96869fa273f0eb43ceaeff3da396ac53aa31ea222b5a3d23715b1ba2a6be75caf59c86b0c6a16000009edce1de3ee2b948f07a14ac8d3ab3fa24f9eb8db9bbcfefe6d6fe8f1dbdcdaa88a8dcdd46ae7b74988b9f43ceed8725f0eb9a08a000007b9385f1f655450044c4d0b8bb2f11dcf05b9bace763f4ced28644fcf5d0ed70d127a0a71ee4e8383c1cd492bb59608222a000288a07ba2a9e3ea8a00050ad553cd3d23ce3d58a504d1fd3e35d24839a42233656388ad1cc9e6d4093a65013348c720800007bb90cfe4ead158306ecfa738ecdb37391e2bd7f96dcf3f669d3f673acd9a2aaeb23b355ceb45de96e741e6df9fe57aa261e655fd4d8787c1dcf17957152445453dc258dde5eb2d0bb5b735a66b3d789445446b92aa72bda374f23abeb7c5769cb3a7b5d245dfcda3e7d2ae3cdcae92e5d82da3ab90792fb07151c045621c462a29eeaa49e4eb03e1d3f465f52e55ef9b4b04e820da0008e44392d9d329327572f3526833b9cb9350b86b52757e96f50d269e274fa8e6b1211523dd5e1e4eb57583d78540e99a7782900100122084941133825bb981869e780b18746aa02f29e6a18408133fffc4002b1000020202010401040202030100000000010200030411120510132021142223303133324015243442ffda000801010001050218d4c38d4cfa7a67d3d33e9e99f4f4cfa7a67d3d33e9ea9e0aa6655585e222a2f070234d99c8ce467233919c8ce467233919c8ce467233919c8ce4d167f30fbef733bf89ff00cb4687fd23ecb5ea67f66fe2c1f2c3e48fdeb0cdfe8ea1fcc3fc38fb6ac66bedead48a491e9a9a9a86a6e1fbedc8e2733fcb50894e3be44c6c64c7afabb7933021636d46b6d4e302ca716cb4e1f49541d75c293edc7d0ce7f3dae274f484b7307e4a712db653d3044ac20b4f14f0be564262578540c5bb25d7a3dc62744694748a52574ad72d211336cf2de7d89dfa5a622f7c74e79563ff00dbc5c4d90a077c8436558d8cb426451e697d8b8c1728115dc8fdfad5de3c579c49847a9edbedaedb136274ff009aeca7fef8f72372ca0a14e168d9a4ceb05adc8aba5d8f337c78e187b30f80b351f6154b194fd2c38d538c7a4529a1bf72c04e40cb93e46acaf1f60705de68b7c7ff0010cd2ee8f6017e2db543e8477d43a00d4d7cc5a9aa4f72751ee2ec46e6a28b26293cabfedd8dc6204cdea75d532ba85b746ee3b6fb33812aa19c81fa0cf108140f43a13c9c6d622d98f6796bea955d657702a4c3e96bdab9013222e33b4aa94aff00d0c87e7639fcf757c9705f7619d5ba7f9058a4130f7750c29b9a9607e3f719919111d6918d5b728b485ba113aa74ef24b50a96f460084b1f1cd7723afa721b27428b0daddcde3cd0b0e57259637d22935d295fb199d894dc336914dbd899b858095d06e2a343b5efe34c74e299967d94a704760a265e48ac6258de46cbac578eecd9a4e856fcbb0707b98b91ce5d92a8337a96e3b1633c764345ba55756a92811597d2cfcb92ed13f26513a0cde4c9778e0597e0fdb464a796ec6b3fed65bf1af01bf13beaba32d74876a966ee8ff6e5e6f955c987becc3f32c4ac4c6c43cfb58dc5714fe3b5cbd9832c6dca0eeccab3857c952baece18742e92e50af9afbae83c323359bc18a3c36a1f8a9bf3df68aebea193e2c9bf3c582c209f463a18d57c0ec4ea65bfe2b98534509c530dbf1d8dc5701f9435fd4e435158aebabccaead8c6c01eb2774dc08192fcb1af61a57fb717e57ab3138f984d91a1f51f7dd19f514c63b97fc8cab41ca5b558637c5b9a772afc57f4e1f84cc6c6f0b65a359978c3755f530b75f1637095d496e0db6eb0b11c35190dcb269af95972f073e965810622150cf37dee3a2ae9c870788cd5e51fbf33368269c21aa3b10222f1c9b122aee0c2f22518a68c567e6986781c8cb2b958b94964ea3f1947bdb6701571e4d9490e5d627d6d52ab96dedd49b82555012c6e52c42d574efc96ea587c6c0ec42c37af9d40ba225a36995c716cbaf08ecdb21c837da6d6ef600f3c090528278d614f2b548a82672ec661d50df654bf32840aa4e8065b1151d67e732ba82b7716fe5bee365fd6cfe70c344f6dfbdd669abbab41f5331ed36adabcd35c81e4b5e2efc549fb59430b139201a1eb754b64575ac75bff00d2dfa8a8338099078a8b6bc75076326b305890e422cc6b41f5301d8e6bb27b7891475eff00d2dfacc1cefb6ca56ba6af8ad8ea23d573d883c78acd54a2cf433c0e27d2ae850fb99372d35e75e6fb8febd40a14641d97b1502d6d733dbe0b6b7f2518ea1a9fbf1e62e42d83d49999d46ba066663e4393fb89b0e4554f18265ff7601de3629fc71a9d1a73606066fb6464252b9dd51ac8edb9b87fd059d4dd4df466b535d59de38bd4960ea3598d954b85c8f1cafab2897f56a8264e4bdce5a13fe9ddfda6376dcdcdcdc630fecd7e83fe193fdc7b30f40238f8d4d4d4d4d4d7b8f9f4770b3c76bcf05b3c778995664568fb24cdcdc237d809fc4fe674cc6aef73d1e932ee8c443d2efdaf48b8cbfa5db5ab2ea1f55eec788c34e6ddd941999d3034baa6ac910ace338c0b0244ac96e9b87e05eda9a8cbb9d670f8161ea20ed92772b1c57d6da52d195d2a5b535675d809454d6be1612d026fd6fac589994f86e3ea3b51f7e4c66d1f6be84b5737a73553531e86b9f0f152842788e6f90462086a7ae51773ed7370ac7caf5ea3e1bd752cfb530d38d3323fc47b91b99dd3f6d878eb457330f376b3c66bc8dafd5996ff0020ec669d63a7f86755e5a2d5e2c7d04cafe951a5992374d4769fa97eecc1aaf25cf9ecb6ce5461fdd898a77567ff00501f0c3e3abd1e2c93eb61e568ec7f8c4febf52753c89aaed4b076afe32dd15c555aae5902627f8e27f8e47dd90219d6eae58eddff00ffc4001d110002020203010000000000000000000001110030102002214050ffda0008010301013f01d4de706e7f21c771f2a8a83b3a4ea71c77516870bc67ce639d61c7573b498e1b0eea8379bbffc400221100020201040203010000000000000000000102112003101230132104315132ffda0008010201013f01bdadef117747e85da9090976a5ebba2bb10a228576a212ef8c6c5857543d959de7a6ab7e4597b3ce2e8f2906dfb1ed792c5316a086b3592669bfd3992c5747234d5b1c5d1c594532babe37f4711c4a251f5d30d3fd3c68d34a2c4f6a18e8a2878415e09d11d5392252b2cbd9e1a2b2b1e2f7d2face58bdbfffc40032100001020404040406020301000000000001000203112131101241512022306132337181132340425292627291a1b104ffda0008010100063f02f261fea17930ff0050bc987fa85e4c3fd42f2a1feabca87faaf2a1feabca87faaf2a1feabca67ea8498d1ecac1784637572ae55cab9572ae55cab9572ae55cab9572ae7a6301f5650c07d60c5ab2b54286dd074334b97e82404f4e00196dd49a13bb51480595d7e0018d59a354ec9b05961d7937c46814387b54fae1cada7740c6332a4d120894eca2e517baae0139e1a6aab25cce5cdccb944913b27bfbf4e5c05da31453f8d17c58a264e9c0e68d549a1341f08332100187d94f2157ff0038902eea614eadd3ceee429435e855668343b2a8e6d909d59ff300c60264a6fe56af8706aed4f48e5132be7070f457aff25e1594594f5e854abacecf10ff006bb14e63bed539554a08a9537beaa6d702b9987a355c8da6ea4e766e8902721b2f29ce5e4b82f964fa393c3848eca2296b85510ce62b61d0a54acd17fc74ab5541c1328821666522053d755f28d36443afc529f29b2b3573be5e8a82bbfd036137d4a8635536d1e2c54516c3e2431ccadc322b244336e87e87242abffe290e78a6ebe2c4f11c1cf1f76262421cdb291123c355f9435307865ae0e77d9a700862b865d54a7958ae405ca38f985775943a7c35539656eea5892a67c46eb236eea20d0a67096a8bcb090a73f641cefb85b00789dfc4c9733a48b61a99be175752ff00d0e737d14c107d550f006fdaca9c3b3306b7415381768d53d5c66b9292b9eea1e6ba2a5a844ad493740a7b76c22b7f2135f30f1555abb041eea76c669d10fdc56416152a23bbe114f7923ba9207b2adcd53226c57aa7377aa20593468e18453dd4ca6be53e55273288c84b866b3bfc47fd701ff08760abe237447f228929ff00d9653e06dd1934594285a5ca01d561d511ba6b4dc3a4839b76a98d5348350a69cefc8a6b45c94d23f1e36b7dcf0b47f2418e3ca2a572b828adee9ac1f714f1a1aacff919e0e339cd65ac916ead3240b6d3ae112169a22478daa7b84d926374026a2b4e944e6f117bfc47859ea9d11ff715cb42b98d1c80fc42ccdf104c1db8220deaa9844313c46ca2875ca86d1e8a233629c5be88e8e4ee1f891dc27a0d97882f161c8700420e3b2ccc1ee87e413e211db06bbedb1c653af090a1374515c0dc6130a66fc150acbc2ac16468a6a549b8349b4d1926f696145353d14e13e9b155207a2cd39bb80b0d364d870ec2ae40764ecdb53a5977d549ab95a4a265244146144b8591ed9f70866be122b28a297156e138421f74a6bdba750aca4db941a4a9841ecf1057961316e298b294eb8d06b343d3aa4b68ddd3b5324df4c0802642320b9bc078b96210d5acf75cd1096e05ce29cf3d5a26b372bbeca7128dd94410c56683b7522a9ccc57e32273729badb75df26f6054dd576077c0fa9c334332283630ca775438cdee922d8746aafd19c8b2e552cbaaa85aaa95f2a2fb152883dc2f9666e5379fa57cf7fad7fa277d4f75a3578c2d0a3c94449e896bc556abe5ba6bc2ab453bf4cc676b6e0aa2e8543b293db2e301a2ab33bc678be2c314d7a2d862ee400d38a4f682a704fb293c4b803582ab776fc65a6c539bd073b46d301df8e4f6cd1743ab70cac0a97dd4ca942a337557394e1b89ec54889385f02e4108a3df8c9409b9ae00ec7a39a10f5400be0c8435a9421426ccc93b3d0b6e167c9f2b74d8ccf7c1c827044714b7a20307207a6f3b092885f4981251c3365f0d8c76694a4b29bd90ed4406e4624e8eaf1436fbf04b6e32733642e66a70ded7ff00533c6277135cc269e1a25cb83ffb14ff00ec54167be25da8e0ffc400291001000201040104010501010100000000010011211031415161207181f19130a1b1c1e1d1f040ffda0008010100013f21f4760db6df559f459f459f458ff898ff008a967f7831cbfc7324db5d4a0d07e227b8f727db4fb69f6d3eda7db4fb69f6d3eda7dbc3fdf9f7d3eda7dbcfbb9da6205695123a32831a0798387c43949bdd0feb5b5369bb068c74a8055e83155537bdccc8f43586247d1502547d43b8885463e874cc47c1186c4b5a1d26972f50f8c5b304a952a10412377839891f492e31f4b04959651de956172b727373740e5ee78ccb2b4b29e768cdba88c660df3064300c1990d0fa70331de2462ade05a43417298622d1f7e334251de768f8a140a00e0961600b8661645ea1dc6e0c5e6b5db02c0456c9365da04063c450b40b8ac738697d3723a32d6939d81125d9b55f30aef020791217108a0a254a8e5d22a1f25f2f70037e508a4856c26d27e0b9b351eb09e633c149041b417da512bd1bf1163d636c0caa806e91e841e44978f01fc4015ad6ac3340a881fb38b053acefca37a2f8b81b2cfccf0a1246c8019a4ea547d0ce11a15972f011c51fc21b0106c067923a45b5c6a53dd0f5314c10d908947ae0e915edff00f151e40550f64768f945573817a8b90db79eda44b0a95cfa213a254df79499cc089613b67f8bafd00394250b1be5399ee19fe5b05b1f0626e515f4f89fb83f89cc1e1a12a82521fe396429d24c9f49837988c8e0807086d246c07e86421320b5799b184a8ca8eda8ed8f29cb13996f6e783cca4ad6c1e653fcaed0e841bc3a1d692af2b68adfdcb99f61d40b8fc92bf558c47379b4ea027ce4fc4f3908600b614e98213503b873150a13720d5c4a7b7012962484d9f45e972f561e85447edb9e21f239b496fb360ea331af4a4d2e21601cc731da83860d588c1642b43d5c9080e0deb70441b1bc045d8864f0f3d6f1375a777ad292db6ea7e083bc6a0ea4e61d8c7bf557989dabaa24a58c2b4a90ecaa080aff000b95fd351e5eb04f707512899200e5012d6625e2e500e78085db1d4e22aaef6ed10b72d40e4814366711841d9a75d92c0d9b111a51592fbc60b6b4cf446d42e7ba01b21427ed44503abb45fe88704af0415755fcc0c9113d886249e02df31dbc845e9d98193140465dbc47a2dd83c685ca9a16ba4a70abee5a3b222cb98d5137023d32e8b4b679a03ba1bbd5b43244992f0da5c165c11d272cfc4f3747fa947cb5fc4b18db4c4b40e5825adb3599e23c6667b0809df37b42c6e104e240d26c5d19674c6fccdb5c67c692c9d710c2b1a44a3614aa45f45ba9483bcd50312c870cd3cff2821cd0132bd981adbc0f69128bf7411bb77dd057ca4c9494c3129e2749c2a11f9474ef18809b7494e16900e9ab8ec795d030d7ebab31687d07a3186d0b64caac570e4b710719be4cd84fce11c33b2310daa4b12de40223ccb8f271e0841967e2a5e13002c85632aaae45f8cb96375941ca9a8c7d672629ca4b83588a70fa57072f04f6c3fa27191b4343b1281055af69f4db0ee62ab9e316cca6dff89e0d1aa9bacca38c0239c4cfb8fbd3cfa99fb267438fca74e5c4cb310fda9f7f30147673e805eb9e213eeb30bea857f4859bbf8852d81a2ee8ba82f1ff00096d51541da3c7159075010dace1d1f0408d9a0c6f3888cb9d048c32c2c59509ed82acd975d4445e6134449d0f54753eaea8b3ce11b27d34c0a0fc5e20a0ad0b62338dc4b880a6612941ef307ef961b2c01710f2f709dd61c2543a49b97d0665157e51a8cb61fc4799cb2e80be9040d2a8d8ee8c9c7e23dca3931b5079943c0d7b92d906a883de81dcb29d465b4ce39735d4201b1887a184ddf812201505dcb2e79c28c7d210254d90653c25075654cc24dea01ec3148aff007495b3674cec13b0838f6a5d97e8556b06c2fc2700f08016b469801afdc9432e77451f5840d312e3c58608a4cec30d3781005b8204ade350b5bb4ad3c07a87816c7683a9b29e62c4ea13671d4d4b89610630139087047fa040951b12a068953ef63ed32acf01012bd784216a12fcdc4a1365b0b375bf2416103a3a5ca614097046cb5c340fac8435e2273dee3886aebb98732a3f2657fbb3ff0045cc69331bfaf6c23f8610cb0742cab28f9f372c556ad630bf44f4868a9f7996aa39800073143d8672926f5494e1af24b7675dd142941dbd16226bb7ac5febde9c2f44bf72d141169f4fd011318caffe13a4d40cb368e35ba614756e9b09ea548eb486eb6202bb07c44766f88ab127f4bb5a63de8627a8c5206c888cb98545ec221ec6ca80612cb67d83030c9521b571ea2b460f4b864d095789413385fa944a9505a024b0305bc29960753c25e2f70a5c916c13696acf88449595803683bc1b7413d1847a59dc35f107690af4ba541bdf312f8df98cd3a16945fb42ea0bbc054a764b35600b615157d871ed07a2b4dc3ed621b412bcaa1b6ae89728c3cb99447fae39c3ff0023a94a23ce0d70045d4af1e7378e7de3d8e7c93276483a78488ef765cc09b6206626b5731da2f1644ec0a131e8cccbd7529236f65c21a19eee8dff000727f0b0081e42115c1651831ff44a84d9d23fb5963f51d0dc7d0343cb923f74a8382b4a078b9e7e34b8fa5d1de1f1146c0170cb97786dccb7b26c36812d90cadede0bc937ef2a2752ea4acba90c4d09728d39bcd9a6748b13bb4fde71a5eb42dc4f8ad0a226146e8ff097182a744500079952a531296c4787834a0fde2d36682a4cb0c74fffda000c0301000200030000001036e194b6e733b4d35997100189954ccbade113dc05e1110a710fa1703c7c40cc2c87e25cbed7a4539f368ade54a95cba95d645c7906d61d84e930ef8945a58d21c60255231045e28c54e35a5d81047acc9d7280be4a59dd358a1bc51b4af5d6fae61faa8eb52e9221bc4b206177c4b09dd86203f1bdcf2e76b0154052ec7c1b8c6dcf0c2b49f3dd716bab3ded7d8b405270c32eb7b30a75a155ded5df6dfff00a54a37c50d1d4f7efdc69ed5bbeac76a0018732a6d86213ba7492482cc06b99f2b6664fd85534d74f9a7c548df8fd87ffa37fcf637bf07ffc4001d110003010101010101010000000000000000011110202131304151ffda0008010301013f10c84123e8fe0f963e91f44f383163e210a37e8d18fd26421f07a8a36363652f2f26b1b1bfc10f5c0c37cd2f0c589b3210489c35758c79082c2108243f321e0624421f047e88834370b77d32091090f928d8de7839785ac5f30d5f3294c9e960d8f868904c7cc3eb1f147887c3706eb190852eb41295bfa79fe90337f087c0d88ac4d8ba79fe058eda8342c42e5e30f5a1a121747fbdf77ab50f3fffc4001e11000300030101010101000000000000000001111021312030415161ffda0008010201013f10b2bc09b2b19b475f29e45dfce10a2a6af989088ce2128a7c96ce08435b16be6946b3b1894fa3423d2ddfd523f431229848984d8d9084121aca3614090913f46ee1e2d8ff0007a1bc2222121a33836d636289898c7847428fc12d1c0c4dc168a3c7062e6907b1ab48d951f744f14b4e3d407ca21b884d51f84be666a41da8fcc1ff0002c6a207fe7b824cd7721a26c912e86b13c41216d50c6a8a2e8d36b659a28c467a1a60994502d282c376a5f4c6a547eef83542ac221e11d5f945f07984e6c5b57091e17b3f4e32b98ef1ffc400281001000202010402010403010000000000010011213141105161817191b120a1f0f1c1d1e130ffda0008010100013f1041fc8fa83afe37a8ff0028fc468fe07d4f0ff1fe98d7fc5fa8d5fc1fa9fc27fc4e0ff27c417f3ff69667d6e87e0834147c60ca693930d53ec14969a47c30abf9e20ff913fb24fec93fbfcfeff3fbfcfeff000ffbf87fdec0bfe6cfedf3fbfcfefb3fb2c35918ed293204358cc4c54b74063a20b66416a2a3e97c818caf944145e60dc2cafd350975065cb964b20c0d12e9f32acc300e947a1221b26407c4c69039f3139a094d060dc4d3cc1a29a179956e0e88ca60e618d609aeb72c80c62010080663cac58c60895169bc4609be8303c4c6b6a6a12361660737090094f2ba890e3a8d21e111e23aea5e71187b03289cd0e25c1be95150a4b731b132e2ce63063cc66ea50d5b1594e099032cca8abb7b428d58062bc4a67c7396a9883660fcb0239d80224a64b07130d90aa35846abae18fb87a525f6dcc31b96bd434b705ca815d4b2d4e6654e908257420da6d9966542fc0a376f30256b7773985d80aa9bd03b44d4df8e0262e9404350aa93c4b726d4b16627d65872980870c75aa985b27c2e6041ddc0cc485c334ea057dc080a104b19869b2e08370663be812a2840198b6cd58ca2fa8e57ca4c45458a12c62fba0045a63bbd895e4baeb0f784c61a028802b1a8c0eea62f1310875e54ae6620bf8cc102b4486727a710f52b3e2cb429c323cc18c198985ad1b8cae7fecb233784748889c3d29096c0251443acc0cf28b0ac75a840228e66184f6b87ee57783cb587bd3529cb5ef8bc60802a8952b1d1a7a6716a0b84960832ec15d3b4c507b9e206b8e6da9912ce0732b1d69136c304aaf3b99920cbb3c1305f7efd66d96f7866543794f3b8e2ddc7581d77402338bb11cc2f8a54afb842829868cbc3bbe58aa9a15871074b971d7454cc40fcb1ac9f70a53647d12b6a0387879f944cb2636993ea25905ba666d05c3a779b7225bccc653cb11b002c9d4151e97c06b95665c31b82602036a4b2a83764a5abc515ec970cdce60d75d45cf5bc70196a2534c1de0d175de340aa1de78e382f3bed2e05ac59b778b2b58a3f642c0ef2d9fa96240a805ab12a45ade0c61f8e88ec5cbd55c50ab953301716195519d8ab5ab3d1f309000c5040cc77d3516e2d4b81514a6fb414a7eec4e723b12a6820f0446c2a15da10c605ddb1b3e65f1df6187720e182f6037294ce2e1f68831e83b236606e0e8090390d68eddaf88e16ecbb2891c91cd2d0cf98ca5102a0f463162bd2ea3beba445652002c0a686730e601f8691c31da07270fcc773f01d602519948f4bc445cc5486463dc352a2985663e087f6f88c95a9cc9e18bd8e84773bc1c74c25c44a45cb2e2d4cba2f4245666718ff00218dd9ac176f6bec4b4ba43a3da64544b841e411efcbb80d65df880d59a1446c08014cc2e253377e2610032153bdd1bd8f888a7669693c420245a971a5c0561b071f31d7a0b5ed19368fa9b62cdc409d4acedaab50307436a2d994bf78ba53bc495cfc9e66a40ca2d60501d5cea3829c4286df70538eb0bee12ed98e3c4a5ea303980724ab9262ddb8bbb99b811b3d200cad2ad63a9c47a6bf2174476cf976d62280415e7707c0575de63cad5ee738ae5be2532294b960fc5de942ad0f05bb43d8f83178fcc037a39bed1b508de1c13957a967a7dc4f3c4ac6e2a55b9866f03cec8451e2d9464d8f18a77315e62e65b52f922db81c5b2d34bc0db3e619039cc65a8780120e2e6e208b3db2f1ae19a80f3e23a5a6cfe52d0b46499a87171e3f33005d530d45e02f306a3901e344a662140c3c0a95c7cddff950966a9b677751cdaa447f69ad6b59ec435ab2aeadc4164a2c06a36b807b2566f8dc37282277aab97ffde4d24a8dfd466e2e605a59e47dc3383b3980ad1d60afc423f8f2d7dc5546e52a25f846bbcc023bdf2c41694541fa8a8c52a3e3097b68bfd1685f0472a2bbfbb10934ba0bb53309573809b6da89776f6f961220659bf2803363bc973819fe40cc42221dea961988cf114df8562580c80fa2637cd07cb2f0768bae62211e6e270e125045e86a167946bbc3d88cb87040cb5166b986677026da9f6a98414ee566bfdc4d964de5619fb3667cc5f70db16b90508ebd128e78100a91522dba8a796d3802d4b9baae7f9f898d1805449acd67ce2107696f8e6270b915c164cb9582f35752e1aafd1536e907e2ea5b0d1af45cc2c2a3dd4a0a8e3be86e15cc04b343f06a50282835f10801999ef460821710354b3e998bc09c9344c9af80732ad2898fe60b43919b0dc34d07e6a23836e7a710582b06fb4ca815fc0899548560532c4bf2a6f2978968613bb4ee58ecc5532f0ad55ed50e622d1dd47632721cb654d67044ecc66f183b2e09749670f7ccde039fbf4bd3b4b3b6341b5ec430ab6af41129acf30965cc58638bb8cd4a02fa2e20d34d5a86b1052e2358fc128828f4e4d5c7c9fa0586697036e89381b08f51f32860161050d64980d53daf52b5b83c40a45e389b40743a9a95cc9ccdf0cdb703e56cc3b7d41ed51f306a9e0952b261384a5594fb74267a62d16072be21b32998e1fee1e07c6636b2fd98da563cc394c4b49b2e0517783e4a8be5d743ac2d60d9e87c5d6713d2798ab82507149b8650390a8c40d4a4d17a60e642c46f12c2564717288550585e522129d42262fb4c59df685540f7f92a17c2d0eea4244ea58e15aad6666642422614af351f51620acbe2599b3cca07ecc0b8205724003d1e60b3215825c352ccc0aba51276e62e66e7c732a572647d400817c073105164bda50cbbcc36435433198f511543c58560748aedc352e30ab00b2eb147a3ce05439d940fc5ca9d968af988ed6531b45bea8caa55f1017f08603983508728b32e29fb4094468da379f30e81a59f3c44cf432fa121c2ad0b5ed7194c0aeea815771792ee08e0d7b5f80437700202b054770d4b8b30488b95d278ff00917640d89b6f988366462ae63cefaae5cbea4784c921e481301ea28214a864f30af3a5936ed665002cf860b8e19be6228d7c981204d22cb81014f2e000537cc0ea051405c2c06d3091ca724b8b041cb10425d00157f28880275f199922b7f5066a51d009555c6614cb96993bd460154399563ee407ea215032aba807a45206496cc1e26d23b5f2b88397496108b996616048af3b612d0f12d4055bcb37142836337f31055554312eadb97d4c6214760993a1fa43307408b812c4a6570eca09b3b5bf083c5d856ac5a132636bccad8e17608408d90e1e608251bf72ef976ff89000df67713bcbb981d1442bad1ee6281435e638d0d0e025e5711dbd6e5cb970c18e83a25afc41561da0730b35d6be195176f88eac89b942ec7fbc3a3b418e053de2f71b7575e8882078b6dee0628e446e51f306392dc0b962bb3a07445add8572b2e96318feaaae83708c60bb941a806a8469629f761356cddc650476f56dcfc0e8901f4448bae2182f0e108c0436f211e43d830228fb305e03b4b2fe622ddc6e4dff00e0ec9c40cf55840e18a0abd92a4193fef33416d371688a5ccf69ee4cccebcb2f43cc76e258331513ff000b76955d094d44825c103fd47ed0aa67fcb331c4544d9198559972e39be2688d45e4cf685b863ce2769e4473898653d6e5cc85cda54ba8baf64ed8016440d982df8a6b197958c45b70b2cc434b2db70ee5c00b6e5ee184645cac333cca0165440a2fd46ffc9aac4a6d0aee514834399540be4613f64cc15361dc2a691a7c32a35fa2a28635064891c2005b33b4b470ee81129fc225fea25682912c998a33770fc47865ca619ce213709cdaa28ecf72e52abc403cbf319062832b31d2a5534ec418f5505293ee29c1f500e0fa8a0d1de21c69701c3de517287f476a2fa8e4f31aca870e3941ae8c7d4b96cb8e61bdc7235d2992215d9cff00aa332d7863e673d47b51163646f0607766d83c19f5031c799558d9f306684661d711330e21f7b809c30af4da7d257b895d5444d4b56e0bc598ef7e6a56856a600b11f34b1d85eba44822923a30fa3dc554df57985b148986254577c20124ecdaf398d883b5d4540cb332f9088aa1f6323c10ad6b3c255bcde4bb9e221ee161b3398aee11c3e25bbd719b2571575ae12d0bd0bbdaf3018cefbba1df6bfc2c74be3aba8b2e55c1502264816956e107982050b7daca0c31124b58703fdc7a9bb2683cc6b2fc79e4f12f8b34b397357509880a2bcbdfe3105b5959129ed03f7210aab5867855d5f7e19b2343e987abcc592a5f4f84f91035c2fa425345b7fa6666bbadfda5e0e85d5e805b813149ba968d29e796d967841a081996042bcb346ea133ad133ed035a4879208ab55fa9693f05822b854b2a5d94c425c97656aa54d4a3a8a662333c5355a9a4d0688b508eb238a80a5741f68b84b8c0e62c0b401b565dff7592dd1ee3512a001f2a622e652c7d7d6e219e1c203f5ab4d3705601e5a801b4115b1aff24ec80fe2277b7061268ca23e218e653bca9fffd9);
INSERT INTO `egitmenler` (`id`, `name`, `surname`, `tel`, `about`, `price`, `photo`) VALUES

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `entry_exit`
--

CREATE TABLE `entry_exit` (
  `id` int(11) NOT NULL,
  `customer_id` int(11) NOT NULL,
  `type` tinyint(1) NOT NULL,
  `date` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `entry_exit`
--

INSERT INTO `entry_exit` (`id`, `customer_id`, `type`, `date`) VALUES
(63, 2, 0, '2022-01-04 08:18:41'),
(64, 2, 1, '2022-01-04 08:18:41'),
(65, 2, 0, '2022-01-04 08:30:21'),
(66, 2, 1, '2022-01-04 08:30:36');

-- --------------------------------------------------------

--
-- Tablo için tablo yapısı `unittype`
--

CREATE TABLE `unittype` (
  `Type` char(1) COLLATE utf8_bin NOT NULL,
  `Seq` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- Tablo döküm verisi `unittype`
--

INSERT INTO `unittype` (`Type`, `Seq`) VALUES
('G', 2),
('L', 3),
('U', 1);

--
-- Dökümü yapılmış tablolar için indeksler
--

--
-- Tablo için indeksler `besinler`
--
ALTER TABLE `besinler`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `besintype`
--
ALTER TABLE `besintype`
  ADD PRIMARY KEY (`Type`);

--
-- Tablo için indeksler `customer`
--
ALTER TABLE `customer`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `egitmenler`
--
ALTER TABLE `egitmenler`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `entry_exit`
--
ALTER TABLE `entry_exit`
  ADD PRIMARY KEY (`id`);

--
-- Tablo için indeksler `unittype`
--
ALTER TABLE `unittype`
  ADD PRIMARY KEY (`Type`);

--
-- Dökümü yapılmış tablolar için AUTO_INCREMENT değeri
--

--
-- Tablo için AUTO_INCREMENT değeri `besinler`
--
ALTER TABLE `besinler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `customer`
--
ALTER TABLE `customer`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- Tablo için AUTO_INCREMENT değeri `egitmenler`
--
ALTER TABLE `egitmenler`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=5;

--
-- Tablo için AUTO_INCREMENT değeri `entry_exit`
--
ALTER TABLE `entry_exit`
  MODIFY `id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=68;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;