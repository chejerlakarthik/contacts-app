import json
import os
import sys
import zipfile

def parse(value):
    tokens = value.split('=')
    env_config_element = {}
    if len(tokens) != 2:
        print('Invalid environment config')
        return sys.exit(-1)
    else:
        env_config_element['option_name'] = tokens[0]
        env_config_element['value'] = tokens[1]
        return env_config_element

def create_configuration(property_strings):
    configuration = {}
    option_settings = []
    for pstring in property_strings.split(','):
        parsed_property = parse(pstring)
        if parsed_property.keys:
            option_settings.append(parsed_property)
    configuration['option_settings'] = option_settings
    return json.dumps(configuration)

def get_all_file_paths(directory):
    # initializing empty file paths list
    file_paths = []

    # crawling through directory and subdirectories
    for root, directories, files in os.walk(directory):
        for filename in files:
            # join the two strings in order to form the full filepath.
            filepath = os.path.join(root, filename)
            file_paths.append(filepath)

            # returning all file paths
    return file_paths

def zip_files(folders = ['target', '.ebextensions'], files = ['Dockerrun.aws.json'], zipFileName = 'aws_ebs_deployment_artifact.zip'):
    print('Zipping files now...')
    with zipfile.ZipFile(zipFileName, 'w') as zipper:
        # Add multiple files to the zip
        for folder in folders:
            for file in get_all_file_paths(folder):
                zipper.write(file)
        for file in files:
            zipper.write(file)
    print('Zipfile ' + zipFileName + ' created')

if __name__ == '__main__':
    if len(sys.argv) == 1:
        print('No environment variables to set')
    else:
        print('Creating environment configuration')
        config_file = ".ebextensions/options.config"
        os.makedirs(os.path.dirname(config_file), exist_ok=True)

        json_config = create_configuration(sys.argv[1])
        with open(config_file, "w") as f:
            print(json_config)
            f.write(json_config)

        try:
            zip_files(zipFileName = sys.argv[2])
        except IndexError:
            zip_files(zipFileName = 'contacts-app-deployment-artifact.zip')

    print('Exiting normally..')
