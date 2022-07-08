export function toBrLocaleString(date: string) {
    return (new Date(date)).toLocaleDateString("pt-BR")
}

export default toBrLocaleString