import React from 'react'
import { useNavigate } from 'react-router-dom'

const HomeClient = (props) => {
    const { loggedIn, email } = props
    const navigate = useNavigate()

    const onButtonClick = () => {
        let path = '/log-in';
        navigate(path);
    }

    return (
        <div className="mainContainer" style={{
            // backgroundImage: `url(/images/dragon.jpg)`,
            position: 'absolute',
            top: 0,
            right: 0,
            bottom: 0,
            left: 0,
            backgroundSize: 'cover',
            backgroundPosition: 'center',
            backgroundRepeat: 'no-repeat',
        }}>
            <div></div>
        </div>
    )
}

export default HomeClient