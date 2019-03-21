node {
    properties([parameters([string(defaultValue: '127.0.0.1', description: 'Plearse give IP to build the site', name: 'IP', trim: false)])])
    stage("Install git") {
        sh "ssh ec2-user@${IP}    sudo yum install  python-pip git -y"
    }
    stage("Clone repo") {
        git 'https://github.com/kolomyya/Flaskex.git'
    }
    stage("Copy files") {
        sh  "scp  -r * ec2-user@${IP}:/tmp/"
    }
    stage("Intall requirements") {
        sh "ssh ec2-user@${IP}    sudo pip install -r /tmp/requirements.txt"
    }
    stage("Run App") {
        sh "ssh ec2-user@${IP}   python /tmp/app.py"
    }
}
