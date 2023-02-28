-- noinspection SqlNoDataSourceInspectionForFile

--
-- Table structure for table `contact`
--

CREATE TABLE `contact` (
  `id` int(11) AUTO_INCREMENT PRIMARY KEY,
  `email` varchar(255) DEFAULT NULL,
  `first_name` varchar(255) DEFAULT NULL,
  `last_name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `title` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `topic`
--

CREATE TABLE `topic` (
  `id` int(11) AUTO_INCREMENT PRIMARY KEY,
  `code_key` varchar(255) DEFAULT NULL,
  `description` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `intent`
--

CREATE TABLE `intent` (
  `id` int(11) AUTO_INCREMENT PRIMARY KEY,
  `remote_host` varchar(255) DEFAULT NULL,
  `topic_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
   FOREIGN KEY (topic_id) REFERENCES topic(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `lead`
--

CREATE TABLE `lead` (
  `id` int(11) AUTO_INCREMENT PRIMARY KEY,
  `email` varchar(255) DEFAULT NULL,
  `message` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `subject` varchar(255) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `template`
--

CREATE TABLE `template` (
  `id` int(11) AUTO_INCREMENT PRIMARY KEY,
  `code_key` varchar(255) DEFAULT NULL,
  `template_text` TEXT DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Table structure for table `message`
--

CREATE TABLE `message` (
  `id` int(11) AUTO_INCREMENT PRIMARY KEY,
  `type` varchar(16) DEFAULT NULL,
  `body` TEXT DEFAULT NULL,
  `template_id` int(11) DEFAULT NULL,
  `contact_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  FOREIGN KEY (contact_id) REFERENCES contact(id),
  FOREIGN KEY (template_id) REFERENCES template(id)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Seed data
--

INSERT INTO topic (id, code_key, name, description)
  VALUES (1, 'cta-processes-impact-delivery', 'Process Impact Delivery', 'CTA Process Impact Delivery (Learn More) Was Clicked On');
INSERT INTO topic (id, code_key, name, description)
  VALUES (2, 'cta-processes-impact-delivery-contact', 'Process Impact Delivery', 'CTA Process Impact Delivery (Contact Us) Was Clicked On');
INSERT INTO topic (id, code_key, name, description)
  VALUES (3, 'cta-independent-analysis', 'Independent Analysis', 'CTA Independent Analysis (Learn More) Was Clicked On');
INSERT INTO topic (id, code_key, name, description)
  VALUES (4, 'cta-independent-analysis-contact', 'Independent Analysis', 'CTA Independent Analysis (Contact Us) Was Clicked On');
INSERT INTO topic (id, code_key, name, description)
  VALUES (5, 'cta-advice', 'Advice', 'Advice (Learn More) Was Clicked On');
INSERT INTO topic (id, code_key, name, description)
  VALUES (6, 'cta-advice-contact', 'Advice', 'CTA Advice (Contact Us) Was Clicked On');
INSERT INTO topic (id, code_key, name, description)
  VALUES (7, 'cta-software-bootstrapping', 'Software Bootstrapping', 'CTA Advice Was Clicked On');
INSERT INTO topic (id, code_key, name, description)
  VALUES (8, 'cta-ops-performance', 'Ops Performance', 'CTA Ops Performance Was Clicked On');
INSERT INTO topic (id, code_key, name, description)
  VALUES (9, 'cta-platform-selection', 'Platform Selection', 'CTA Platform Selection Was Clicked On');
INSERT INTO topic (id, code_key, name, description)
  VALUES (10, 'cta-workforce-management', 'Workforce Management', 'CTA Workforce Management Was Clicked On');
INSERT INTO topic (id, code_key, name, description)
  VALUES (11, 'cta-lean-agile-process-optimization', 'Lean Agile Process Optimization', 'CTA Lean Agile Process Optimization Was Clicked On');
INSERT INTO topic (id, code_key, name, description)
  VALUES (12, 'cta-technology-roadmap-planning', 'Technology Roadmap Planning', 'CTA Technology Roadmap Planning Was Clicked On');
INSERT INTO topic (id, code_key, name, description)
  VALUES (13, 'cta-second-opinion', 'Second Opinion ', 'CTA Second Opinion Planning Was Clicked On');
INSERT INTO topic (id, code_key, name, description)
  VALUES (14, 'cta-bulls-eyes', 'Bulls Eyes', 'CTA Bulls Eyes Clicked On');

INSERT INTO template (id, code_key, template_text)
  VALUES (
  1,
  'contact_us',
  '<html>\n
        <head>\n
            <title>{{subject}}</title>\n
        </head>\n
        <body>\n
            <table>\n
                <tr>\n
                    <td>Name:</td>\n
                    <td>{{name}}</td>\n
                </tr>\n
                <tr>\n
                    <td>Email:</td>\n
                    <td>{{email}}</td>\n
                </tr>\n
                <tr>\n
                    <td>Phone:</td>\n
                    <td>{{phone}}</td>\n
                </tr>\n
                <tr>\n
                    <td>Subject:</td>\n
                    <td>{{subject}}</td>\n
                </tr>\n
                <tr>\n
                    <td>Message:</td>\n
                    <td>{{message}}</td>\n
                </tr>\n
            </table>\n
        </body>\n
</html>\n'
);